package com.earl.ktorchatapp.ui.chat.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.databinding.FragmentRoomsBinding

class RoomsFragment : BaseFragment<FragmentRoomsBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRoomsBinding.inflate(inflater, container, false)

    companion object {

        fun newInstance() = RoomsFragment()
    }
}