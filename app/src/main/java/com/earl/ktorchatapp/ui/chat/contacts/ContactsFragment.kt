package com.earl.ktorchatapp.ui.chat.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.earl.ktorchatapp.KtorChatApp
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.databinding.FragmentContactsBinding
import com.earl.ktorchatapp.ui.NavigationContract
import com.earl.ktorchatapp.ui.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ContactsFragment : BaseFragment<FragmentContactsBinding>() {

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
        viewModel = ViewModelProvider(this, viewModelFactory)[ContactsViewModel::class.java]
        startSession()
        recycler()
    }

    private fun startSession() {
        viewModel.initSession(
            preferenceManager.getString(Keys.KEY_NAME) ?: "",
            preferenceManager.getString(Keys.KEY_TOKEN) ?: ""
        )
    }

    private fun recycler() {
        val adapter = ContactsRecyclerAdapter()
        binding.recycler.adapter = adapter
        lifecycleScope.launchWhenStarted {
            viewModel._contacts
                .onEach {
                        contact ->
                    adapter.submitList(contact)
                }.collect()
        }
    }

    companion object {

        fun newInstance() = ContactsFragment()
    }
}