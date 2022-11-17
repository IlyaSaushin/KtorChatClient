package com.earl.ktorchatapp.domain.mappers

import com.earl.ktorchatapp.data.mappers.UserInfoDataToDomainMapper
import com.earl.ktorchatapp.domain.models.DomainUserInfo
import javax.inject.Inject

class BaseUserInfoDataToDomainMapper @Inject constructor(): UserInfoDataToDomainMapper<DomainUserInfo> {

    override fun map(
        email: String,
        username: String,
        bio: String,
        pic: String
    ) = DomainUserInfo.Base(
        email,
        username,
        bio,
        pic
    )
}