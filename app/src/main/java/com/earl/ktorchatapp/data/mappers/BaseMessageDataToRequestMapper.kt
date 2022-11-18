package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.remote.RequestMessageDto
import javax.inject.Inject

class BaseMessageDataToRequestMapper @Inject constructor(): MessageDataToRequestMapper<RequestMessageDto> {

    override fun map(
        messageId: String,
        roomId: String,
        authorId: String,
        timestamp: String,
        messageText: String
    ) = RequestMessageDto(roomId, authorId, timestamp, messageText)
}