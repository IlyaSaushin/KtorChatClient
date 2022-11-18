package com.earl.ktorchatapp.ui.mappers

import com.earl.ktorchatapp.domain.mappers.MessageDomainToUiMapper
import com.earl.ktorchatapp.ui.models.UiMessage
import javax.inject.Inject

class BaseMessageDomainToUiMapper @Inject constructor(): MessageDomainToUiMapper<UiMessage> {

    override fun map(
        messageId: String,
        roomId: String,
        authorId: String,
        timestamp: String,
        messageText: String
    ) = UiMessage.Base(messageId, roomId, authorId, timestamp, messageText)
}