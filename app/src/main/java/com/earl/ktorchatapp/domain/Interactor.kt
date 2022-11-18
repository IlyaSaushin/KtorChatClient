package com.earl.ktorchatapp.domain

import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.domain.models.DomainLoginDto
import com.earl.ktorchatapp.domain.models.DomainRegisterDto
import com.earl.ktorchatapp.domain.models.DomainUserInfo
import javax.inject.Inject

interface Interactor {

    suspend fun login(dto: DomainLoginDto, callback: OperationResultListener)

    suspend fun register(dto: DomainRegisterDto, callback: OperationResultListener)

    suspend fun fetchUserInfo(token: String) : DomainUserInfo

    suspend fun fetchContacts(token: String) : List<DomainUserInfo>

    suspend fun fetchAllUser(username: String) : List<DomainUserInfo>

    suspend fun removeUserFromContact(userUsername: String, contactUsername: String)

    suspend fun addUserToContacts(userUsername: String, contactUsername: String)

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

        override suspend fun fetchContacts(token: String) = repository.fetchContacts(token)

        override suspend fun fetchAllUser(username: String) = repository.fetchAllUsers(username)

        override suspend fun removeUserFromContact(userUsername: String, contactUsername: String) {
            repository.removeUserFromContacts(userUsername, contactUsername)
        }

        override suspend fun addUserToContacts(userUsername: String, contactUsername: String) {
            repository.addUserToContacts(userUsername, contactUsername)
        }
    }
}