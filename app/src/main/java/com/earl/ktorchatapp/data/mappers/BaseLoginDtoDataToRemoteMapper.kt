package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.remote.RequestLoginDto
import javax.inject.Inject

class BaseLoginDtoDataToRemoteMapper @Inject constructor() : LoginDtoDataToRemoteMapper<RequestLoginDto> {

    override fun map(input: String, password: String) = RequestLoginDto(input, password)
}