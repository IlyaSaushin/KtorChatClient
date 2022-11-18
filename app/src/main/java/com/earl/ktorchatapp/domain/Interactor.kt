package com.earl.ktorchatapp.domain

import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.core.Resource
import com.earl.ktorchatapp.domain.models.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface Interactor {

    suspend fun login(dto: DomainLoginDto, callback: OperationResultListener)

    suspend fun register(dto: DomainRegisterDto, callback: OperationResultListener)

    suspend fun fetchUserInfo(token: String) : DomainUserInfo

    suspend fun fetchContacts(token: String) : List<DomainUserInfo>

    suspend fun fetchAllUser(username: String) : List<DomainUserInfo>

    suspend fun removeUserFromContact(userUsername: String, contactUsername: String)

    suspend fun addUserToContacts(userUsername: String, contactUsername: String)

    suspend fun addRoom(name: String, private: String, author: String, users: List<String>) : String

    suspend fun initChatSocketSession(username: String, roomToken: String) : Resource<Unit>

    suspend fun fetchMessagesForRoom(roomToken: String) : List<DomainMessage>

    suspend fun observeNewMessages() : Flow<DomainMessage>

    suspend fun sendMessage(message: DomainMessage)

    suspend fun fetchRooms(token: String) : List<DomainChatRoom>

    suspend fun closeSession()

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

        override suspend fun addRoom(name: String, private: String, author: String, users: List<String>) =
            repository.addRoom(name, private, author, users)

        override suspend fun initChatSocketSession(username: String, roomToken: String) =
            webSocketRepository.initChatSession(username, roomToken)

        override suspend fun closeSession() {
            webSocketRepository.closeSession()
        }

        override suspend fun fetchMessagesForRoom(roomToken: String) =
            repository.fetchMessages(roomToken)

        override suspend fun sendMessage(message: DomainMessage) {
            webSocketRepository.sendMessage(message)
        }

        override suspend fun observeNewMessages() = webSocketRepository.observeMessages()

        override suspend fun fetchRooms(token: String) = repository.fetchRooms(token)
    }
}