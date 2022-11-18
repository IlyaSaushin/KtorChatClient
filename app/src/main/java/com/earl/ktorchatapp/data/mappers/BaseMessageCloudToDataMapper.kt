package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.DataMessage
import javax.inject.Inject

class BaseMessageCloudToDataMapper @Inject constructor() : MessageCloudToDataMapper<DataMessage> {

    override fun map(
        messageId: String,
        roomId: String,
        authorId: String,
        timestamp: String,
        messageText: String
    ) = DataMessage.Base(messageId, roomId, authorId, timestamp, messageText)
}