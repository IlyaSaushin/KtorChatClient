package com.earl.ktorchatapp.domain.mappers

import com.earl.ktorchatapp.domain.models.DomainRegisterDto
import com.earl.ktorchatapp.ui.mappers.UiRegisterDtoUiToDomainMapper

class BaseRegisterDtoUIToDomainMapper : UiRegisterDtoUiToDomainMapper<DomainRegisterDto> {

    override fun map(
        email: String,
        username: String,
        password: String,
        bio: String,
        pic: String
    ) = DomainRegisterDto.Base(email, username, password, bio, pic)
}