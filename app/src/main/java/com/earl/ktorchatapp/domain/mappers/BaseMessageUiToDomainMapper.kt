package com.earl.ktorchatapp.domain.mappers

import com.earl.ktorchatapp.domain.models.DomainMessage
import com.earl.ktorchatapp.ui.mappers.MessageUiToDomainMapper
import javax.inject.Inject

class BaseMessageUiToDomainMapper @Inject constructor(): MessageUiToDomainMapper<DomainMessage> {

    override fun map(
        messageId: String,
        roomId: String,
        authorId: String,
        timestamp: String,
        messageText: String
    ) = DomainMessage.Base(messageId, roomId, authorId, timestamp, messageText)
}