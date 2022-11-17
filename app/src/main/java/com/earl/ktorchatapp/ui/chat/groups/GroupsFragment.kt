package com.earl.ktorchatapp.ui.chat.groups

import android.view.LayoutInflater
import android.view.ViewGroup
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.databinding.FragmentGroupsBinding

class GroupsFragment : BaseFragment<FragmentGroupsBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGroupsBinding.inflate(inflater, container, false)

    companion object {

        fun newInstance() = GroupsFragment()
    }
}