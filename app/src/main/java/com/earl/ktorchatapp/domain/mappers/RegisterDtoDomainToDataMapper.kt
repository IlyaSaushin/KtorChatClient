package com.earl.ktorchatapp.domain.mappers

interface RegisterDtoDomainToDataMapper <T> {

    fun map(
        email: String,
        username: String,
        password: String,
        bio: String,
        pic: String
    ) : T
}