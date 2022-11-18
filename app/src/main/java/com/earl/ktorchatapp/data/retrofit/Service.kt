package com.earl.ktorchatapp.data.retrofit

import android.app.DownloadManager.Request
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

//    @Headers("Content-Type: application/json")
//    @POST("/fetchRoomsForUser")
//    fun fetchRoomsForUser(
//        @Body token: TokenDto?
//    ): Call<List<RoomResponseDto?>?>?
//
    @Headers("Content-Type: application/json")
    @POST("/users")
    suspend fun fetchUsersList(
        @Body fetchAllUsers: RequestUsernameDto
    ): List<RemoteUserInfo>
//
//    @Headers("Content-Type: application/json")
//    @POST("/messages")
//    fun fetchMessagesForRoom(
//        @Body roomToken: TokenDto?
//    ): Call<List<MessageResponseDto?>?>?
//
//    @Headers("Content-Type: application/json")
//    @POST("/addRoom")
//    fun addRoom(
//        @Body room: NewRoomRequestDto?
//    ): Call<TokenDto?>?
//
    @Headers("Content-Type: application/json")
    @POST("/fetchUserInfo")
    suspend fun fetchUserInfo(
        @Body token: RequestTokenDto
    ): RemoteUserInfo
//
    @Headers("Content-Type: application/json")
    @POST("/contacts")
    suspend fun fetchContacts(
        @Body token: RequestTokenDto
    ): List<RemoteUserInfo>

    @Headers("Content-Type: application/json")
    @POST("/addContact")
    fun addContact(
        @Body addContactDto: RequestAddContactDto
    )
//
//    @Headers("Content-Type: application/json")
//    @POST("/removeContact")
//    fun removeContact(
//        @Body removeUserFromContactsDto: RemoveUserFromContactsDto?
//    ): Call<String?>?
}