package com.earl.ktorchatapp.data.models.remote

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RequestNewRoom (
        @SerializedName("name") val name: String,
        @SerializedName("pic") val pic: String,
        @SerializedName("author") val author: String,
        @SerializedName("users")  val users: List<String>
)