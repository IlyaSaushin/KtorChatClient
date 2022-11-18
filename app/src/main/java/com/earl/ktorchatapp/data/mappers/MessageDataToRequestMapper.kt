package com.earl.ktorchatapp.data.mappers

interface MessageDataToRequestMapper<T> {

    fun map(
        messageId: String,
        roomId: String,
        authorId: String,
        timestamp: String,
        messageText: String
    ) : T
}