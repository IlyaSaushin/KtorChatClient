package com.earl.ktorchatapp.data.mappers

interface MessageCloudToDataMapper <T> {

    fun map(
        messageId: String,
        roomId: String,
        authorId: String,
        timestamp: String,
        messageText: String
    ) : T
}