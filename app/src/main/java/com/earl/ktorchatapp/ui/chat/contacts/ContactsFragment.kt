package com.earl.ktorchatapp.ui.chat.contacts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.earl.ktorchatapp.KtorChatApp
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.databinding.FragmentContactsBinding
import com.earl.ktorchatapp.ui.NavigationContract
import com.earl.ktorchatapp.ui.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.security.Key
import javax.inject.Inject

class ContactsFragment : BaseFragment<FragmentContactsBinding>(), OnContactClickListener, OperationResultListener {

    private lateinit var navigator: NavigationContract
    private lateinit var preferenceManager: SharedPreferenceManager
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: ContactsViewModel

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentContactsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as KtorChatApp).appComponent.injectContactsFragment(this)
        navigator = requireActivity() as NavigationContract
        preferenceManager = SharedPreferenceManager(requireContext())
        refreshList()
        viewModel.fetchContacts(preferenceManager.getString(Keys.KEY_TOKEN) ?: "")
        recycler()
        binding.inviteFriend.setOnClickListener {
            navigator.showAddNewContactFragment()
        }
    }

    private fun recycler() {
        val adapter = ContactsRecyclerAdapter(this)
        binding.recycler.adapter = adapter
        viewModel.observeContactsListLiveData(this) {
            adapter.submitList(it)
        }
    }

    override fun removeUserFromContacts(username: String) {
        viewModel.removeUserFromContacts(
            preferenceManager.getString(Keys.KEY_NAME) ?: "",
            username
        )
    }

    private fun refreshList() {
        viewModel = ViewModelProvider(this, viewModelFactory)[ContactsViewModel::class.java]
    }

    override fun startChat(username: String) {
        val userUsername = preferenceManager.getString(Keys.KEY_NAME) ?: ""
        viewModel.startChat(
            username,
            "private",
            userUsername,
            listOf(userUsername, username),
            this
        )
    }

    override fun <T> success(success: T) {
        val token = success as String
        navigator.chat(token)
    }

    override fun fail(exception: Exception?) {
        Log.d("tag", "contacts fragment fail: $exception")
    }

    companion object {

        fun newInstance() = ContactsFragment()
    }
}