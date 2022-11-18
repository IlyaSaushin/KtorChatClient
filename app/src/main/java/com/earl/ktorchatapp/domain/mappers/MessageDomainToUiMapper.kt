package com.earl.ktorchatapp.domain.mappers

interface MessageDomainToUiMapper<T> {

    fun map(
        messageId: String,
        roomId: String,
        authorId: String,
        timestamp: String,
        messageText: String
    ) : T
}