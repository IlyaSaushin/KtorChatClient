package com.earl.ktorchatapp.domain.mappers

import com.earl.ktorchatapp.data.mappers.MessageDataToDomainMapper
import com.earl.ktorchatapp.domain.models.DomainMessage
import javax.inject.Inject

class BaseMessageDataToDomainMapper @Inject constructor(): MessageDataToDomainMapper<DomainMessage> {

    override fun map(
        messageId: String,
        roomId: String,
        authorId: String,
        timestamp: String,
        messageText: String
    ) = DomainMessage.Base(messageId, roomId, authorId, timestamp, messageText)
}