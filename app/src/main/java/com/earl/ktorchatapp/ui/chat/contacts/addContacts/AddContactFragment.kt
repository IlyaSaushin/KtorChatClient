package com.earl.ktorchatapp.ui.chat.contacts.addContacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.earl.ktorchatapp.KtorChatApp
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.databinding.FragmentAddNewContactBinding
import com.earl.ktorchatapp.ui.NavigationContract
import com.earl.ktorchatapp.ui.ViewModelFactory
import javax.inject.Inject

class AddContactFragment : BaseFragment<FragmentAddNewContactBinding>(), GlobalUserCLickListener {

    private lateinit var navigator: NavigationContract
    private lateinit var preferenceManager: SharedPreferenceManager
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var viewModel: AddContactViewModel

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddNewContactBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as KtorChatApp).appComponent.injectAddContactFragment(this)
        navigator = requireActivity() as NavigationContract
        preferenceManager = SharedPreferenceManager(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[AddContactViewModel::class.java]
        fetchAllUsers()
        recycler()
    }

    private fun fetchAllUsers() {
        viewModel.fetchAllUsers(preferenceManager.getString(Keys.KEY_NAME) ?: "")
    }

    private fun recycler() {
        val adapter = PossibleContactsRecyclerAdapter(this)
        binding.usersRecycler.adapter = adapter
        viewModel.observeUsersListLiveData(this) {
            adapter.submitList(it)
        }
    }

    override fun addUserToContacts(username: String) {
        viewModel.addUserToContacts(
            preferenceManager.getString(Keys.KEY_NAME) ?: "",
            username
        )
    }

    companion object {

        fun newInstance() = AddContactFragment()
    }
}