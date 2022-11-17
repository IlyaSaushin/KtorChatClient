package com.earl.ktorchatapp.domain

import com.earl.ktorchatapp.core.Resource
import com.earl.ktorchatapp.domain.models.DomainUserInfo
import kotlinx.coroutines.flow.Flow

interface WebSocketRepository {

    suspend fun initContactsSession(username: String) : Resource<Unit>

    suspend fun observeContacts() : Flow<DomainUserInfo>

    suspend fun closeSession()
}