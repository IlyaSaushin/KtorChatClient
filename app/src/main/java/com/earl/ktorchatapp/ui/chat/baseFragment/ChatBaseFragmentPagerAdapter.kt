package com.earl.ktorchatapp.ui.chat.baseFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.earl.ktorchatapp.ui.chat.contacts.ContactsFragment
import com.earl.ktorchatapp.ui.chat.groups.GroupsFragment
import com.earl.ktorchatapp.ui.chat.profile.ProfileFragment
import com.earl.ktorchatapp.ui.chat.rooms.RoomsFragment

class ChatBaseFragmentPagerAdapter (
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RoomsFragment.newInstance()
            1 -> GroupsFragment.newInstance()
            2 -> ContactsFragment.newInstance()
            else -> ProfileFragment.newInstance()
        }
    }

    companion object {
        private const val ITEM_COUNT = 4
    }
}