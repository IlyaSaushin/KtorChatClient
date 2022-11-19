package com.earl.ktorchatapp.data.retrofit

import com.earl.ktorchatapp.data.mappers.MessageCloudToDataMapper
import com.earl.ktorchatapp.data.mappers.RemoteTokenToDataMapper
import com.earl.ktorchatapp.data.mappers.RoomCloudToDataMapper
import com.earl.ktorchatapp.data.mappers.UserInfoRemoteToDataMapper
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteTokenDto(
    @SerializedName("userToken") val userToken: String
) {
    fun <T> map(mapper: RemoteTokenToDataMapper<T>) = mapper.map(userToken)
}

@Serializable
data class RemoteUserInfo(
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String,
    @SerializedName("bio") val bio: String,
    @SerializedName("pic") val pic: String,
) {
    fun <T> map(mapper: UserInfoRemoteToDataMapper<T>) = mapper.map(email, username, bio, pic)
}

@Serializable
data class RemoteRoomTokenDto(
    @SerializedName("token") val token: String
)

@Serializable
data class RemoteMessageDto(
    @SerializedName("message_id") val message_id : String,
    @SerializedName("room_id") val room_id : String,
    @SerializedName("author_id") val author_id : String,
    @SerializedName("timestamp") val timestamp : String,
    @SerializedName("message") val messageText : String,
) {
    fun <T> map(mapper: MessageCloudToDataMapper<T>) =
        mapper.map(message_id, room_id, author_id, timestamp, messageText)
}

@Serializable
data class RoomResponseDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("icon") val icon: String
) {
    fun <T> map(mapper: RoomCloudToDataMapper<T>) = mapper.map(id, name, icon)
}