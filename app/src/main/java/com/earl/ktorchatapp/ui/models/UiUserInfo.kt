package com.earl.ktorchatapp.ui.models

import android.graphics.BitmapFactory
import android.util.Base64
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

    fun initDetailsAsContact(name: TextView, avatar: RoundedImageView)

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
            val bytes = Base64.decode(pic, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            image.setImageBitmap(bitmap)
            userNickname.text = username
        }

        override fun globalUsersRecyclerDetails(image: RoundedImageView, userNickname: TextView) {
            val bytes = Base64.decode(pic, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            image.setImageBitmap(bitmap)
            userNickname.text = username
        }

        override fun initDetailsAsContact(name: TextView, avatar: RoundedImageView) {
            name.text = username
            val bytes = Base64.decode(pic, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            avatar.setImageBitmap(bitmap)
        }
    }
}