package com.earl.ktorchatapp.ui.models

import android.widget.TextView
import com.bumptech.glide.Glide
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.core.Same
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.makeramen.roundedimageview.RoundedImageView

interface UiUserInfo : Same<UiUserInfo> {

    override fun same(data: UiUserInfo) = data == this

    fun save(preferenceManager: SharedPreferenceManager)

    fun recyclerDetails(image: RoundedImageView, user: TextView)

    class Base(
        private val email: String,
        private val username: String,
        private val bio: String,
        private val pic: String
    ) : UiUserInfo {

        override fun save(preferenceManager: SharedPreferenceManager) {
            preferenceManager.putString(Keys.KEY_IMAGE, pic)
            preferenceManager.putString(Keys.KEY_EMAIL, email)
            preferenceManager.putString(Keys.KEY_NAME, username)
            preferenceManager.putString(Keys.KEY_USER_BIO, bio)
        }

        override fun recyclerDetails(image: RoundedImageView, user: TextView) {
            user.text = username
        }
    }
}