package com.earl.ktorchatapp.ui.mappers

interface LoginDtoUiToDomainMapper<T> {

    fun map(
        input: String,
        password: String
    ) : T
}