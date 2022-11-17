package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.remote.RequestRegisterDto
import javax.inject.Inject

class BaseRegisterDataToRemoteMapper @Inject constructor() : RegisterDtoDataToRemoteMapper<RequestRegisterDto> {

    override fun map(
        email: String,
        username: String,
        password: String,
        bio: String,
        pic: String
    ) = RequestRegisterDto(email, username, password, bio, pic)
}