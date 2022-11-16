package com.earl.ktorchatapp.data.models

import com.earl.ktorchatapp.data.mappers.LoginDtoDataToRemoteMapper

interface DataLoginDto {

    fun <T> map(mapper: LoginDtoDataToRemoteMapper<T>) : T

    class Base(
        val input: String,
        val password: String
    ) : DataLoginDto {
        override fun <T> map(mapper: LoginDtoDataToRemoteMapper<T>) = mapper.map(input, password)
    }
}