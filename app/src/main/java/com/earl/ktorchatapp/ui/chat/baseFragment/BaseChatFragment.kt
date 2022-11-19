package com.earl.ktorchatapp.ui.chat.baseFragment

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.earl.ktorchatapp.KtorChatApp
import com.earl.ktorchatapp.R
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.databinding.FragmentBaseChatBinding
import com.earl.ktorchatapp.ui.NavigationContract
import com.earl.ktorchatapp.ui.ViewModelFactory
import com.earl.ktorchatapp.ui.models.UiUserInfo
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*
import javax.inject.Inject

class BaseChatFragment @Inject constructor() : BaseFragment<FragmentBaseChatBinding>(), OperationResultListener {

    @Inject
    lateinit var viewModel: ChatBaseViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBaseChatBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as KtorChatApp).appComponent.injectChatBaseFragment(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[ChatBaseViewModel::class.java]
        viewPager(requireContext())
        fetchUserInfo()
    }

    private fun viewPager(context: Context) {
        binding.pager.adapter = ChatBaseFragmentPagerAdapter(requireActivity())
        val tabs = binding.tabs
        tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val tabIconColor = ContextCompat.getColor(context, R.color.secondPrimaryBlue)
                Objects.requireNonNull(tab.icon)?.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val tabIconColor = ContextCompat.getColor(context, R.color.tabs)
                Objects.requireNonNull(tab.icon)?.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        TabLayoutMediator(
            binding.tabs, binding.pager
        ) { tab: TabLayout.Tab, position: Int ->
            if (position == 0) {
                tab.text = getString(R.string.chats)
                tab.setIcon(R.drawable.chat_icon)
            } else if (position == 1) {
                tab.text = getString(R.string.gruops)
                tab.setIcon(R.drawable.groups_icon)
            } else if (position == 2) {
                tab.text = getString(R.string.contactss)
                tab.setIcon(R.drawable.contacts_icon)
            } else if (position == 3) {
                tab.text = getString(R.string.profile)
                tab.setIcon(R.drawable.chat_icon)
            }
        }.attach()
    }

    private fun fetchUserInfo() {
        navigator.showProgressBar();
        val token = preferenceManager.getString(Keys.KEY_TOKEN)
        if (token == null) {
            Toast.makeText(
                requireContext(),
                "No personal token, please reload app",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            viewModel.fetchUserInfo(token, this)
        }
    }

    override fun <T> success(success: T) {
        val userInfo: UiUserInfo = success as UiUserInfo
        userInfo.save(preferenceManager)
        navigator.hideProgressBar()
    }

    override fun fail(exception: Exception?) {
        navigator.hideProgressBar();
        Toast.makeText(
            requireContext(),
            "Unable to get user info, please check server connection",
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {

        fun newInstance() = BaseChatFragment()
    }
}