package com.earl.ktorchatapp.data.mappers

interface RegisterDtoDataToRemoteMapper <T> {

    fun map(
        email: String,
        username: String,
        password: String,
        bio: String,
        pic: String
    ) : T
}