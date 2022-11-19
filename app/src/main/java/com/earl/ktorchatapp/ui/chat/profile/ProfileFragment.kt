package com.earl.ktorchatapp.ui.chat.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.databinding.FragmentProfileBinding
import com.earl.ktorchatapp.ui.NavigationContract

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.test.setOnClickListener {
            preferenceManager.clear()
            navigator.login()
        }
    }

    companion object {

        fun newInstance() = ProfileFragment()
    }
}