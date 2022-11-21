package com.earl.ktorchatapp.data

import com.earl.ktorchatapp.domain.NotificationService
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.http.*

class NotificationServiceImpl : NotificationService {

    override suspend fun sendNotification(senderName: String, receiverName: String, message: String) {
        val client = HttpClient(Android)
        client.get {
            url(NotificationService.SEND_NOTIFICATION)
            contentType(ContentType.Application.Json)
            parameter("sender", senderName)
            parameter("receiver", receiverName)
            parameter("message", message)
        }
    }
}