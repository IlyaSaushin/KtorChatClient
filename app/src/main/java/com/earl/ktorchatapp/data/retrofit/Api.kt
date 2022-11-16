package com.earl.ktorchatapp.data.retrofit

import com.earl.ktorchatapp.data.mappers.RemoteTokenToDataMapper
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteTokenDto(
    @SerializedName("userToken") private val token: String
) {
    fun <T> map(mapper: RemoteTokenToDataMapper<T>) = mapper.map(token)
}