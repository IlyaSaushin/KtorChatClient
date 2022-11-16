package com.earl.ktorchatapp.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.earl.ktorchatapp.R
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.databinding.ActivityMainBinding
import com.earl.ktorchatapp.ui.chat.baseFragment.BaseChatFragment
import com.earl.ktorchatapp.ui.login.LoginFragment

class MainActivity : AppCompatActivity(), NavigationContract {

    var progressBar: Dialog? = null
    var preferenceManager: SharedPreferenceManager? = null

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preferenceManager = SharedPreferenceManager(this)
        val isSignedUp = preferenceManager!!.getBoolean(Keys.KEY_IS_SIGNED_UP)
        if (isSignedUp) {
            chat()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commit()
        }
    }

    override fun login() {
        showFragment(LoginFragment.newInstance())
    }

    override fun register() {
        TODO("Not yet implemented")
    }

    override fun showAddDetailsFragment(email: String?, password: String?) {
        TODO("Not yet implemented")
    }

    override fun chat() {
        showFragment(BaseChatFragment.newInstance())
    }

    override fun contacts() {
        TODO("Not yet implemented")
    }

    override fun showAddNewContactFragment() {
        TODO("Not yet implemented")
    }

    override fun back() {
        TODO("Not yet implemented")
    }

    override fun showProgressBar() {
        progressBar = Dialog(this, android.R.style.Theme_Black)
        val view = LayoutInflater.from(this).inflate(R.layout.progress_bar, null)
        progressBar!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressBar!!.window!!.setBackgroundDrawableResource(R.color.custom_transparent)
        progressBar!!.setContentView(view)
        progressBar!!.show()
    }

    override fun hideProgressBar() {
        progressBar?.dismiss()
    }

    override fun closeApp() {
        TODO("Not yet implemented")
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}