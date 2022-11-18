package com.earl.ktorchatapp.domain

import com.earl.ktorchatapp.core.Resource
import com.earl.ktorchatapp.domain.models.DomainMessage
import kotlinx.coroutines.flow.Flow

interface WebSocketRepository {

    suspend fun initChatSession(username: String, roomToken: String) : Resource<Unit>

    suspend fun observeMessages() : Flow<DomainMessage>

    suspend fun sendMessage(message: DomainMessage)

    suspend fun closeSession()
}