package com.earl.ktorchatapp.ui.chat.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.earl.ktorchatapp.KtorChatApp
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.databinding.FragmentContactsBinding
import com.earl.ktorchatapp.ui.ViewModelFactory
import com.earl.ktorchatapp.ui.models.UiChatRoom
import javax.inject.Inject

class ContactsFragment : BaseFragment<FragmentContactsBinding>(), OnContactClickListener {

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

    override fun startChat(contactUsername: String) {
        navigator.showProgressBar()
        viewModel.fetchAllRoomsContacts()
        viewModel.observeAllRoomsContactsLiveData(this) {
            val room : UiChatRoom? = it.find { it.name() == contactUsername }
            if (room == null) {
                val userUsername = preferenceManager.getString(Keys.KEY_NAME) ?: ""
                viewModel.startChat(
                    contactUsername,
                    preferenceManager.getString(Keys.KEY_IMAGE) ?: "",
                    userUsername,
                    listOf(userUsername, contactUsername)
                )
                viewModel.observeNewRoomTokenLiveData(this) {
                    viewModel.insertNewRoomContactsIntoDb(it, contactUsername)
                    navigator.chat(it, contactUsername)
                    navigator.hideProgressBar()
                }
            } else {
                navigator.hideProgressBar()
                navigator.chat(room.token(), room.name())
            }
        }
    }

    companion object {

        fun newInstance() = ContactsFragment()
    }
}