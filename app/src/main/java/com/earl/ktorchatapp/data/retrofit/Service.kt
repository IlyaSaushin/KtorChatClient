package com.earl.ktorchatapp.data.retrofit

import com.earl.ktorchatapp.data.models.remote.*
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Service {

    @Headers("Content-Type: application/json")
    @POST("/register")
    suspend fun register(
        @Body registerDto: RequestRegisterDto
    ): RemoteTokenDto

    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun login(
        @Body loginDto: RequestLoginDto
    ): RemoteTokenDto

    @Headers("Content-Type: application/json")
    @POST("/fetchRoomsForUser")
    suspend fun fetchRoomsForUser(
        @Body token: RequestTokenDto
    ): List<RoomResponseDto>

    @Headers("Content-Type: application/json")
    @POST("/users")
    suspend fun fetchUsersList(
        @Body fetchAllUsers: RequestUsernameDto
    ): List<RemoteUserInfo>

    @Headers("Content-Type: application/json")
    @POST("/messages")
    suspend fun fetchMessagesForRoom(
        @Body roomToken: RequestRoomToken
    ) : List<RemoteMessageDto>

    @Headers("Content-Type: application/json")
    @POST("/addRoom")
    suspend fun addRoom(
        @Body room: RequestNewRoom
    ) : RemoteRoomTokenDto

    @Headers("Content-Type: application/json")
    @POST("/fetchUserInfo")
    suspend fun fetchUserInfo(
        @Body token: RequestTokenDto
    ): RemoteUserInfo

    @Headers("Content-Type: application/json")
    @POST("/fetchContactInfo")
    suspend fun fetchContactInfo(
        @Body name: RequestContactNameDto
    ): RemoteUserInfo

    @Headers("Content-Type: application/json")
    @POST("/contacts")
    suspend fun fetchContacts(
        @Body token: RequestTokenDto
    ): List<RemoteUserInfo>

    @Headers("Content-Type: application/json")
    @POST("/addContact")
    suspend fun addContact(
        @Body addContactDto: RequestAddContactDto
    )

    @Headers("Content-Type: application/json")
    @POST("/removeContact")
    suspend fun removeContact(
        @Body removeUserFromContactsDto: RequestRemoveUserFromContactDto
    )
}