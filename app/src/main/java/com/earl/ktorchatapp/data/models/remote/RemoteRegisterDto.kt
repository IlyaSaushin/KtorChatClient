package com.earl.ktorchatapp.data.models.remote

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class RemoteRegisterDto (
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("bio") val bio: String,
    @SerializedName("pic") val pic: String
)