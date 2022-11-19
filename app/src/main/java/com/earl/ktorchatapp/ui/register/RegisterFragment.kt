package com.earl.ktorchatapp.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.earl.ktorchatapp.KtorChatApp
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.databinding.FragmentRegisterBinding
import com.earl.ktorchatapp.ui.NavigationContract
import javax.inject.Inject

class RegisterFragment @Inject constructor(): BaseFragment<FragmentRegisterBinding>() {

    @Inject
    lateinit var validation: RegisterDetailsFormValidation


    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as KtorChatApp).appComponent.injectRegisterFragment(this)
        binding.iconBack.setOnClickListener { navigator.back() }
        binding.createAccButton.setOnClickListener { registerDetailsFragment() }
    }

    private fun isValidate(): Boolean {
        return validation.validate(
            binding.emailEd,
            binding.passwordEd,
            binding.passwordTwiceEd
        )
    }

    private fun registerDetailsFragment() {
        if (isValidate()) {
            navigator.showAddDetailsFragment(binding.emailEd.text.toString(), binding.passwordEd.text.toString())
        }
    }

    companion object {

        fun newInstance() = RegisterFragment()
    }
}