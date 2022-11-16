package com.earl.ktorchatapp.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.earl.ktorchatapp.KtorChatApp
import com.earl.ktorchatapp.core.BaseFragment
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.databinding.FragmentLoginBinding
import com.earl.ktorchatapp.ui.NavigationContract
import com.earl.ktorchatapp.ui.ViewModelFactory
import com.earl.ktorchatapp.ui.models.UiLoginDto
import javax.inject.Inject

class LoginFragment @Inject constructor() : BaseFragment<FragmentLoginBinding>(), OperationResultListener {

    @Inject
    lateinit var validation: LoginFormValidation
    @Inject
    lateinit var viewModel: LoginFragmentViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var navigator: NavigationContract
    private lateinit var preferenceManager: SharedPreferenceManager

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as KtorChatApp).appComponent.injectLoginFragment(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginFragmentViewModel::class.java]
        navigator = requireActivity() as NavigationContract
        preferenceManager = SharedPreferenceManager(requireContext())
        binding.logInButton.setOnClickListener { logIn() }
        binding.signUpButton.setOnClickListener { navigator.register() }
    }

    private fun isValidate(): Boolean {
        return validation.validate(
            binding.logInEmail,
            binding.logInPassword
        )
    }

    private fun logIn() {
        if (isValidate()) {
            navigator.showProgressBar()
            val user = UiLoginDto.Base(
                binding.logInEmail.text.toString().trim(),
                binding.logInPassword.text.toString().trim()
            )
            viewModel.login(user, this)
        }
    }

    override fun <T> success(success: T) {
        navigator.hideProgressBar()
        preferenceManager.putBoolean(Keys.KEY_IS_SIGNED_UP, true)
        preferenceManager.putString(Keys.KEY_TOKEN, success.toString())
//        Toast.makeText(requireContext(), success.toString(), Toast.LENGTH_SHORT).show()
        navigator.chat()
    }

    override fun fail(exception: Exception?) {
        navigator.hideProgressBar()
//        Toast.makeText(requireContext(), "Error $exception", Toast.LENGTH_SHORT).show()
        Log.d("tag", "fail: error $exception")
    }

    companion object {

        fun newInstance() = LoginFragment()
    }
}