package com.earl.ktorchatapp.data.models.remote

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RequestContactNameDto (
        @SerializedName("contactName") val contactName: String
)