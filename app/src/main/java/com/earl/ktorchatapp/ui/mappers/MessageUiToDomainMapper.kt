package com.earl.ktorchatapp.ui.mappers

interface MessageUiToDomainMapper<T> {

    fun map(
        messageId: String,
        roomId: String,
        authorId: String,
        timestamp: String,
        messageText: String
    ) : T
}