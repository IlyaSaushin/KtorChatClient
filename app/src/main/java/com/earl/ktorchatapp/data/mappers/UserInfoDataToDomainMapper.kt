package com.earl.ktorchatapp.data.mappers

interface UserInfoDataToDomainMapper <T> {

    fun map(
        email: String,
        username: String,
        bio: String,
        pic: String
    ) : T
}