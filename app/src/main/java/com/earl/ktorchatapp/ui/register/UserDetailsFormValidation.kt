package com.earl.ktorchatapp.ui.register

import android.widget.EditText
import android.widget.Toast
import com.earl.ktorchatapp.R
import javax.inject.Inject

interface UserDetailsFormValidation {
    fun validate(
        image: String,
        name: EditText,
        nickName: EditText,
        bio: EditText
    ): Boolean

    class BaseUserDetailsValidation @Inject constructor() : UserDetailsFormValidation {
        override fun validate(
            image: String,
            name: EditText,
            nickName: EditText,
            bio: EditText
        ): Boolean {
            var validate = true
            val context = name.context
            if (image == null) {
                validate = false
                Toast.makeText(context, "Choose your profile image, please", Toast.LENGTH_SHORT)
                    .show()
            } else if (name.text.toString().trim { it <= ' ' }.isEmpty()) {
                validate = false
                name.error = context.getString(R.string.empty_string_err)
            } else if (nickName.text.toString().trim { it <= ' ' }.isEmpty()) {
                validate = false
                nickName.error = context.getString(R.string.empty_string_err)
            } else if (bio.text.toString().trim { it <= ' ' }.isEmpty()) {
                validate = false
                bio.error = context.getString(R.string.empty_string_err)
            }
            return validate
        }
    }
}