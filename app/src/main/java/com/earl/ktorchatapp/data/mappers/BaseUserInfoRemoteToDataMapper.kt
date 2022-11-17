package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.DataUserInfo
import javax.inject.Inject

class BaseUserInfoRemoteToDataMapper @Inject constructor() : UserInfoRemoteToDataMapper<DataUserInfo> {

    override fun map(
        email: String,
        username: String,
        bio: String,
        pic: String
    ) = DataUserInfo.Base(
        email,
        username,
        bio,
        pic
    )
}