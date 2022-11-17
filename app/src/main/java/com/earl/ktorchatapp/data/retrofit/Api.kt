package com.earl.ktorchatapp.data.retrofit

import com.earl.ktorchatapp.data.mappers.RemoteTokenToDataMapper
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