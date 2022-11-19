package com.earl.ktorchatapp.ui.chat.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.earl.ktorchatapp.KtorChatApp
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.databinding.FragmentRoomsBinding
import com.earl.ktorchatapp.ui.NavigationContract
import com.earl.ktorchatapp.ui.ViewModelFactory
import javax.inject.Inject

class RoomsFragment : BaseFragment<FragmentRoomsBinding>(), OnRoomClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var viewModel: RoomsViewModel

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRoomsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as KtorChatApp).appComponent.injectRoomsFragment(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[RoomsViewModel::class.java]
        fetchRooms()
        recycler()
    }

    private fun fetchRooms() {
        viewModel.fetchRooms(preferenceManager.getString(Keys.KEY_TOKEN) ?: "")
    }

    private fun recycler() {
        val adapter = RoomsRecyclerAdapter(this)
        binding.recycler.adapter = adapter
        viewModel.observeRoomsLivedata(this) {
            adapter.submitList(it)
        }
    }

    override fun joinChatRoom(token: String, contactName: String) {
        navigator.chat(token, contactName)
    }

    companion object {

        fun newInstance() = RoomsFragment()
    }
}