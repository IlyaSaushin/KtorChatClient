package com.earl.ktorchatapp.data.models.remote

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RequestAddContactDto (
    @SerializedName("userUsername") val userUsername: String,
    @SerializedName("contactUsername") val contactUsername: String
)