package com.earl.ktorchatapp.ui.models

import android.widget.TextView
import com.earl.ktorchatapp.core.Same
import com.makeramen.roundedimageview.RoundedImageView
import android.util.Base64
import android.graphics.BitmapFactory

interface UiChatRoom : Same<UiChatRoom> {

    override fun same(data: UiChatRoom) = this == data

    fun recyclerDetails(title: TextView, image: RoundedImageView)

    fun token() : String

    fun name() : String

    class Base(
        private val id: String,
        private val name: String,
        private val icon: String
    ) : UiChatRoom {

        override fun token() = id

        override fun name() = name

        override fun recyclerDetails(title: TextView, image: RoundedImageView) {
            title.text = name
            val bytes = Base64.decode(icon, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            image.setImageBitmap(bitmap)
        }
    }
}