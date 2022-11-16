package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.remote.RemoteRegisterDto
import javax.inject.Inject

class BaseRegisterDataToRemoteMapper @Inject constructor() : RegisterDtoDataToRemoteMapper<RemoteRegisterDto> {

    override fun map(
        email: String,
        username: String,
        password: String,
        bio: String,
        pic: String
    ) = RemoteRegisterDto(email, username, password, bio, pic)
}