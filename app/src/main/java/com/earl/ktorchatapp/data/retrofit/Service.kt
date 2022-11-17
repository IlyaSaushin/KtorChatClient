package com.earl.ktorchatapp.data.retrofit

import com.earl.ktorchatapp.data.models.remote.RequestLoginDto
import com.earl.ktorchatapp.data.models.remote.RequestRegisterDto
import com.earl.ktorchatapp.data.models.remote.RequestTokenDto
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
//    @Headers("Content-Type: application/json")
//    @POST("/users")
//    fun fetchUsersList(
//        @Body fetchAllUsers: UserUsernameDto?
//    ): Observable<List<UserInfo?>?>?
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
//
//    @Headers("Content-Type: application/json")
//    @POST("/addContact")
//    fun addContacts(
//        @Body addContactDto: AddContactDto?
//    ): Call<String?>?
//
//    @Headers("Content-Type: application/json")
//    @POST("/removeContact")
//    fun removeContact(
//        @Body removeUserFromContactsDto: RemoveUserFromContactsDto?
//    ): Call<String?>?
}