package com.earl.ktorchatapp.ui.chat.baseFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.databinding.FragmentBaseChatBinding
import javax.inject.Inject

class BaseChatFragment @Inject constructor() : BaseFragment<FragmentBaseChatBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBaseChatBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        fun newInstance() = BaseChatFragment()
    }
}