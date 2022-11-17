package com.earl.ktorchatapp.ui.chat.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    companion object {

        fun newInstance() = ProfileFragment()
    }
}