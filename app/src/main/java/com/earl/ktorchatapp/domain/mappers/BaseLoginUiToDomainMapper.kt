package com.earl.ktorchatapp.domain.mappers

import com.earl.ktorchatapp.domain.models.DomainLoginDto
import com.earl.ktorchatapp.ui.mappers.LoginDtoUiToDomainMapper
import javax.inject.Inject

class BaseLoginUiToDomainMapper @Inject constructor() : LoginDtoUiToDomainMapper<DomainLoginDto> {

    override fun map(input: String, password: String) = DomainLoginDto.Base(input, password)
}