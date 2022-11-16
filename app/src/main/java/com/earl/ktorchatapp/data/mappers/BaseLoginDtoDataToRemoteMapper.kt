package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.remote.RemoteLoginDto
import javax.inject.Inject

class BaseLoginDtoDataToRemoteMapper @Inject constructor() : LoginDtoDataToRemoteMapper<RemoteLoginDto> {

    override fun map(input: String, password: String) = RemoteLoginDto(input, password)
}