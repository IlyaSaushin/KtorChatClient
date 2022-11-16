package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.DataRegisterDto
import com.earl.ktorchatapp.domain.mappers.RegisterDtoDomainToDataMapper

class BaseRegisterDtoDomainToDataMapper : RegisterDtoDomainToDataMapper<DataRegisterDto> {

    override fun map(
        email: String,
        username: String,
        password: String,
        bio: String,
        pic: String
    ) = DataRegisterDto.Base(email, username, password, bio, pic)
}