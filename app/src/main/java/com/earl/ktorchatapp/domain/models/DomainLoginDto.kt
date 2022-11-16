package com.earl.ktorchatapp.domain.models

import com.earl.ktorchatapp.domain.mappers.LoginDtoDomainToDataMapper

interface DomainLoginDto {

    fun <T> map(mapper: LoginDtoDomainToDataMapper<T>) : T

    class Base(
        val input: String,
        val password: String
    ) : DomainLoginDto {
        override fun <T> map(mapper: LoginDtoDomainToDataMapper<T>) = mapper.map(input, password)
    }
}