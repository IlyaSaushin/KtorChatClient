package com.earl.ktorchatapp.ui.models

import android.widget.TextView
import com.earl.ktorchatapp.core.Keys
import com.earl.ktorchatapp.core.Same
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.makeramen.roundedimageview.RoundedImageView

interface UiUserInfo : Same<UiUserInfo> {

    override fun same(data: UiUserInfo) = data == this

    fun save(preferenceManager: SharedPreferenceManager)

    fun contactsRecyclerDetails(image: RoundedImageView, userNickname: TextView)

    fun globalUsersRecyclerDetails(image: RoundedImageView, userNickname: TextView)

    fun username() : String

    class Base(
        private val email: String,
        private val username: String,
        private val bio: String,
        private val pic: String
    ) : UiUserInfo {

        override fun username() = username

        override fun save(preferenceManager: SharedPreferenceManager) {
            preferenceManager.putString(Keys.KEY_IMAGE, pic)
            preferenceManager.putString(Keys.KEY_EMAIL, email)
            preferenceManager.putString(Keys.KEY_NAME, username)
            preferenceManager.putString(Keys.KEY_USER_BIO, bio)
        }

        override fun contactsRecyclerDetails(image: RoundedImageView, userNickname: TextView) {
            userNickname.text = username
        }

        override fun globalUsersRecyclerDetails(image: RoundedImageView, userNickname: TextView) {
            userNickname.text = username
        }
    }
}