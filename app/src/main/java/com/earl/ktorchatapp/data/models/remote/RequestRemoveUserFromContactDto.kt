package com.earl.ktorchatapp.data.models.remote

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RequestRemoveUserFromContactDto(
    @SerializedName("userUsername") private val userUsername: String,
    @SerializedName("contactUsername") private val contactUsername: String
)
