package com.earl.ktorchatapp.data.retrofit

import com.earl.ktorchatapp.data.models.DataLoginDto
import com.earl.ktorchatapp.data.models.remote.RemoteLoginDto
import com.earl.ktorchatapp.data.models.remote.RemoteRegisterDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Service {

    @Headers("Content-Type: application/json")
    @POST("/register")
    suspend fun register(
        @Body registerDto: RemoteRegisterDto
    ): RemoteTokenDto

    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun login(
        @Body loginDto: RemoteLoginDto
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
//    @Headers("Content-Type: application/json")
//    @POST("/fetchUserInfo")
//    fun fetchUserInfo(
//        @Body token: TokenDto?
//    ): Call<UserInfo?>?
//
//    @Headers("Content-Type: application/json")
//    @POST("/contacts")
//    fun fetchContacts(
//        @Body token: TokenDto?
//    ): Flowable<ContactsListResponse?>?
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