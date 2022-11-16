package com.earl.ktorchatapp.domain.mappers

interface LoginDtoDomainToDataMapper<T> {

    fun map(
        input: String,
        password: String
    ) : T
}