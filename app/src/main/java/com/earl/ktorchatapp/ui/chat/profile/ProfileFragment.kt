package com.earl.ktorchatapp.ui.chat.profile

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.earl.ktorchatapp.KtorChatApp
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.databinding.FragmentProfileBinding
import com.earl.ktorchatapp.ui.ViewModelFactory
import javax.inject.Inject

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: ProfileViewModel

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as KtorChatApp).appComponent.injectProfileFragment(this)
        initUserInfo()
        binding.signOut.setOnClickListener {
            preferenceManager.clear()
            navigator.login()
        }
    }

    private fun initUserInfo() {
        navigator.showProgressBar()
        binding.changeEmailEt.setText(preferenceManager.getString(Keys.KEY_EMAIL))
        binding.changePassEt.setText(preferenceManager.getString(Keys.KEY_PASSWORD))
        binding.usersName.text = preferenceManager.getString(Keys.KEY_NAME)
        binding.userBio.text = preferenceManager.getString(Keys.KEY_USER_BIO)
        val pic = preferenceManager.getString(Keys.KEY_IMAGE)
        val bytes = Base64.decode(pic, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        binding.usersAvatar.setImageBitmap(bitmap)
        navigator.hideProgressBar()
    }

    companion object {

        fun newInstance() = ProfileFragment()
    }
}