package com.earl.ktorchatapp.ui.models

import com.earl.ktorchatapp.ui.mappers.LoginDtoUiToDomainMapper

interface UiLoginDto {

    fun <T> map(mapper: LoginDtoUiToDomainMapper<T>) : T

    class Base(
        private val input: String,
        private val password: String
    ) : UiLoginDto {
        override fun <T> map(mapper: LoginDtoUiToDomainMapper<T>) = mapper.map(input, password)
    }
}