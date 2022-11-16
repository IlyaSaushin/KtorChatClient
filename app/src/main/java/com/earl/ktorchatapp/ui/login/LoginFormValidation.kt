package com.earl.ktorchatapp.ui.login

import android.widget.EditText
import com.earl.ktorchatapp.R
import javax.inject.Inject

interface LoginFormValidation {

    fun validate(input: EditText, password: EditText): Boolean

    class BaseValidation @Inject constructor() : LoginFormValidation {

        override fun validate(input: EditText, password: EditText): Boolean {
            var validation = true
            val context = input.context
            if (input.text.toString().trim { it <= ' ' }.isEmpty()) {
                input.error = context.getString(R.string.empty_string_err)
                validation = false
            } else if (password.text.toString().trim { it <= ' ' }.isEmpty()) {
                password.error = context.getString(R.string.empty_string_err)
                validation = false
            } else if (password.text.toString().length < 6) {
                password.error = context.getString(R.string.shor_pass_err)
                validation = false
            }
            return validation
        }
    }
}