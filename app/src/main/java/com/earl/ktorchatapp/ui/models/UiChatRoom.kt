package com.earl.ktorchatapp.ui.models

import android.widget.TextView
import com.earl.ktorchatapp.core.Same

interface UiChatRoom : Same<UiChatRoom> {

    override fun same(data: UiChatRoom) = this == data

    fun details(title: TextView)

    fun token() : String

    class Base(
        private val id: String,
        private val name: String,
        private val private: String
    ) : UiChatRoom {

        override fun token() = id

        override fun details(title: TextView) {
            title.text = name
        }
    }
}