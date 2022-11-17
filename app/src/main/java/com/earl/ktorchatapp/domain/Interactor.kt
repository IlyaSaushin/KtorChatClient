package com.earl.ktorchatapp.domain

import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.core.Resource
import com.earl.ktorchatapp.domain.models.DomainLoginDto
import com.earl.ktorchatapp.domain.models.DomainRegisterDto
import com.earl.ktorchatapp.domain.models.DomainUserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface Interactor {

    suspend fun login(dto: DomainLoginDto, callback: OperationResultListener)

    suspend fun register(dto: DomainRegisterDto, callback: OperationResultListener)

    suspend fun fetchUserInfo(token: String) : DomainUserInfo

    suspend fun initContactsSession(username: String) : Resource<Unit>

    suspend fun fetchContacts(token: String) : List<DomainUserInfo>

    suspend fun observeNewContacts() : Flow<DomainUserInfo>

    suspend fun closeContactsSession()

    class Base @Inject constructor(
        private val repository: Repository,
        private val webSocketRepository: WebSocketRepository,
    ) : Interactor {

        override suspend fun login(dto: DomainLoginDto, callback: OperationResultListener) {
            repository.login(dto, callback)
        }

        override suspend fun register(dto: DomainRegisterDto, callback: OperationResultListener) {
            repository.register(dto, callback)
        }

        override suspend fun fetchUserInfo(token: String) = repository.fetchUserInfo(token)

        override suspend fun initContactsSession(username: String) =
            webSocketRepository.initContactsSession(username)

        override suspend fun observeNewContacts() = webSocketRepository.observeContacts()

        override suspend fun fetchContacts(token: String) = repository.fetchContacts(token)

        override suspend fun closeContactsSession() {
            webSocketRepository.closeSession()
        }
    }
}