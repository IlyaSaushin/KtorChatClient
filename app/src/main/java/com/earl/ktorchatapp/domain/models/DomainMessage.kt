package com.earl.ktorchatapp.domain.models

import com.earl.ktorchatapp.domain.mappers.MessageDomainToDataMapper
import com.earl.ktorchatapp.domain.mappers.MessageDomainToUiMapper

interface DomainMessage {

    fun <T> map(mapper: MessageDomainToUiMapper<T>) : T

    fun <T> mapToData(mapper: MessageDomainToDataMapper<T>) : T

    class Base(
        private val messageId: String,
        private val roomId: String,
        private val authorId: String,
        private val timestamp: String,
        private val messageText: String
    ) : DomainMessage {
        override fun <T> map(mapper: MessageDomainToUiMapper<T>) =
            mapper.map(messageId, roomId, authorId, timestamp, messageText)

        override fun <T> mapToData(mapper: MessageDomainToDataMapper<T>) =
            mapper.map(messageId, roomId, authorId, timestamp, messageText)
    }
}