package com.earl.ktorchatapp.domain

import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.domain.models.*

interface Repository {

    suspend fun login(loginDto: DomainLoginDto, callback: OperationResultListener)

    suspend fun register(registerDto: DomainRegisterDto, callback: OperationResultListener)

    suspend fun fetchUserInfo(userToken: String) : DomainUserInfo

    suspend fun fetchContacts(userToken: String) : List<DomainUserInfo>

    suspend fun fetchAllUsers(username: String) : List<DomainUserInfo>

    suspend fun addUserToContacts(userUsername: String, contactUsername: String)

    suspend fun removeUserFromContacts(userUsername: String, contactUsername: String)

    suspend fun addRoom(name: String, icon: String, author: String, users: List<String>) : String

    suspend fun fetchMessages(roomToken: String) : List<DomainMessage>

    suspend fun fetchRooms(token: String) : List<DomainChatRoom>

    suspend fun fetchContactInfo(name: String) : DomainUserInfo

    suspend fun insertNewRoomContactIntoDb(contactName: String, roomToken: String)

    suspend fun fetchAllRoomsContacts() : List<DomainChatRoom>
}