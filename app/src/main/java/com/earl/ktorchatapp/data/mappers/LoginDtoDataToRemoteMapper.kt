package com.earl.ktorchatapp.data.mappers

interface LoginDtoDataToRemoteMapper <T> {

    fun map(
        input: String,
        password: String
    ) : T
}