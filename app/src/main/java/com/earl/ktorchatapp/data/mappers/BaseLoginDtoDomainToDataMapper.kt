package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.DataLoginDto
import com.earl.ktorchatapp.domain.mappers.LoginDtoDomainToDataMapper
import javax.inject.Inject

class BaseLoginDtoDomainToDataMapper @Inject constructor() : LoginDtoDomainToDataMapper<DataLoginDto> {

    override fun map(input: String, password: String) = DataLoginDto.Base(input, password)
}