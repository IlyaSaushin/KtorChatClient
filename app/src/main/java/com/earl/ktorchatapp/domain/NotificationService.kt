package com.earl.ktorchatapp.domain

interface NotificationService {

    suspend fun sendNotification(senderName: String, receiverName: String, message: String)

    companion object {
        const val SEND_NOTIFICATION = "http://10.0.3.2:8080/sendNotification"
    }
}