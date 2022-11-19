package com.earl.ktorchatapp.ui.chat.rooms.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.earl.ktorchatapp.KtorChatApp
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.databinding.FragmentChatBinding
import com.earl.ktorchatapp.ui.NavigationContract
import com.earl.ktorchatapp.ui.ViewModelFactory
import com.earl.ktorchatapp.ui.models.UiMessage
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

class ChatFragment(
    private val token: String,
    private val contactName: String
) : BaseFragment<FragmentChatBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var viewModel: ChatViewModel

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentChatBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as KtorChatApp).appComponent.injectChatFragment(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[ChatViewModel::class.java]
        initSession()
        initContactDetails()
        recycler()
        binding.sendBtn.setOnClickListener {
            if (!binding.writeText.text.isNullOrEmpty()) {
                sendMessage()
                binding.writeText.text.clear()
            }
        }
        binding.backArrow.setOnClickListener {
            viewModel.closeSession()
            navigator.back()
        }
    }

    private fun initContactDetails() {
        viewModel.initContactDetails(contactName)
        viewModel.observeContactInfo(this) {
            it.initDetailsAsContact(binding.contactName, binding.contactAvatar)
        }
        navigator.hideProgressBar()
    }

    private fun initSession() {
        navigator.showProgressBar()
        viewModel.initSession(
            preferenceManager.getString(Keys.KEY_NAME) ?: "",
            token
        )
    }

    private fun recycler() {
        val adapter = MessagesRecyclerAdapter(preferenceManager.getString(Keys.KEY_TOKEN) ?: "")
        binding.recyclerMessages.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.stackFromEnd = true
        binding.recyclerMessages.layoutManager = linearLayoutManager
        lifecycleScope.launchWhenStarted {
            viewModel._messages
                .onEach {
                        messages ->
                    adapter.submitList(messages)
                    binding.recyclerMessages.layoutManager?.smoothScrollToPosition(binding.recyclerMessages,null, messages.size)
                }
                .collect()
        }
    }

    private fun sendMessage() {
        viewModel.sendMessage(
            UiMessage.Base(
                UUID.randomUUID().toString(),
                token,
                preferenceManager.getString(Keys.KEY_TOKEN) ?: "",
                "timestamp",
                binding.writeText.text.trim().toString()
            )
        )
    }

    companion object {

        fun newInstance(token: String, contactName: String) = ChatFragment(token, contactName)
    }
}