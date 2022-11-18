package com.earl.ktorchatapp.data.models.remote

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RequestMessageDto (
   @SerializedName("room_id") val room_id : String,
   @SerializedName("author_id") val author_id : String,
   @SerializedName("timestamp") val timestamp : String,
   @SerializedName("messageText") val messageText : String,
)