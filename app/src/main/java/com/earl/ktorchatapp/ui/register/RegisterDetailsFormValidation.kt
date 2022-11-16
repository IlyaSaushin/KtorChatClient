package com.earl.ktorchatapp.ui.register

import android.util.Patterns
import android.widget.EditText
import com.earl.ktorchatapp.R
import javax.inject.Inject

interface RegisterDetailsFormValidation {
    fun validate(
        email: EditText,
        firstPassword: EditText,
        secondPassword: EditText
    ): Boolean

    class Base @Inject constructor() : RegisterDetailsFormValidation {
        override fun validate(
            email: EditText,
            firstPassword: EditText,
            secondPassword: EditText
        ): Boolean {
            var validate = true
            val context = email.context
            if (email.text.toString().trim { it <= ' ' }.isEmpty()) {
                email.error = context.getString(R.string.empty_string_err)
                validate = false
            } else if (firstPassword.text.toString().trim { it <= ' ' }.isEmpty()) {
                firstPassword.error = context.getString(R.string.empty_string_err)
                validate = false
            } else if (secondPassword.text.toString().trim { it <= ' ' }.isEmpty()) {
                secondPassword.error = context.getString(R.string.empty_string_err)
                validate = false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString().trim { it <= ' ' })
                    .matches()
            ) {
                email.error = context.getString(R.string.incorrect_email_err)
                validate = false
            } else if (firstPassword.text.toString().trim { it <= ' ' }.length <= 6) {
                firstPassword.error = context.getString(R.string.shor_pass_err)
                validate = false
            } else if (firstPassword.text.toString()
                    .trim { it <= ' ' } != secondPassword.text.toString().trim { it <= ' ' }
            ) {
                secondPassword.error = context.getString(R.string.unequal_pass_err)
                validate = false
            }
            return validate
        }
    }
}
