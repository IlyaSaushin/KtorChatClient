package com.earl.ktorchatapp.domain.mappers

interface UserInfoDomainToUiMapper <T> {

    fun map(
        email: String,
        username: String,
        bio: String,
        pic: String
    ) : T
}