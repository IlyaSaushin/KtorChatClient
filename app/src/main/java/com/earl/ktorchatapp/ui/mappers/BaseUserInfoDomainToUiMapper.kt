package com.earl.ktorchatapp.ui.mappers

import com.earl.ktorchatapp.domain.mappers.UserInfoDomainToUiMapper
import com.earl.ktorchatapp.ui.models.UiUserInfo
import javax.inject.Inject

class BaseUserInfoDomainToUiMapper @Inject constructor() : UserInfoDomainToUiMapper<UiUserInfo> {

    override fun map(
        email: String,
        username: String,
        bio: String,
        pic: String
    ) = UiUserInfo.Base(
        email,
        username,
        bio,
        pic
    )
}