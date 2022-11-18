package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.DataMessage
import com.earl.ktorchatapp.domain.mappers.MessageDomainToDataMapper
import javax.inject.Inject

class BaseMessageDomainToDataMapper @Inject constructor(): MessageDomainToDataMapper<DataMessage> {

    override fun map(
        messageId: String,
        roomId: String,
        authorId: String,
        timestamp: String,
        messageText: String
    ) = DataMessage.Base(messageId, roomId, authorId, timestamp, messageText)
}