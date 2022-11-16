package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.DataTokenDto
import javax.inject.Inject

class BaseTokenRemoteToDataMapper @Inject constructor() : RemoteTokenToDataMapper<DataTokenDto> {

    override fun map(token: String) = DataTokenDto.Base(token)
}