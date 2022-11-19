package com.earl.ktorchatapp.ui.models

import android.widget.TextView
import com.earl.ktorchatapp.core.Same
import com.earl.ktorchatapp.ui.mappers.MessageUiToDomainMapper

interface UiMessage : Same<UiMessage> {

    override fun same(data: UiMessage) = this == data

    fun <T> map(mapper: MessageUiToDomainMapper<T>) : T

    fun recyclerDetails(message: TextView)

    fun isAuthoredMessage(token: String) : Boolean

    class Base(
        private val messageId: String,
        private val roomId: String,
        private val authorId: String,
        private val timestamp: String,
        private val messageText: String
    ) : UiMessage {

        override fun <T> map(mapper: MessageUiToDomainMapper<T>) =
            mapper.map(messageId, roomId, authorId, timestamp, messageText)

        override fun recyclerDetails(message: TextView) {
            message.text = messageText
        }

        override fun isAuthoredMessage(token: String) = token == authorId
    }
}