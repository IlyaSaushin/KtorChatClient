package com.earl.ktorchatapp.ui.mappers

interface UiRegisterDtoUiToDomainMapper<T> {

    fun map(
        email: String,
        username: String,
        password: String,
        bio: String,
        pic: String
    ) : T
}