package com.earl.ktorchatapp.data.models

import com.earl.ktorchatapp.data.mappers.MessageDataToDomainMapper
import com.earl.ktorchatapp.data.mappers.MessageDataToRequestMapper

interface DataMessage {

    fun <T> map(mapper: MessageDataToDomainMapper<T>) : T

    fun <T> mapToRequest(mapper: MessageDataToRequestMapper<T>) : T

    class Base(
        private val messageId: String,
        private val roomId: String,
        private val authorId: String,
        private val timestamp: String,
        private val messageText: String
    ) : DataMessage {

        override fun <T> map(mapper: MessageDataToDomainMapper<T>) =
            mapper.map(messageId, roomId, authorId, timestamp, messageText)

        override fun <T> mapToRequest(mapper: MessageDataToRequestMapper<T>) =
            mapper.map(messageId, roomId, authorId, timestamp, messageText)
    }
}