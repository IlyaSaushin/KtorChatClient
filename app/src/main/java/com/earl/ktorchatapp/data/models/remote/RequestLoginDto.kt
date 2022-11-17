package com.earl.ktorchatapp.data.models.remote

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLoginDto(
    @SerializedName("input") val input: String,
    @SerializedName("password") val password: String,
)