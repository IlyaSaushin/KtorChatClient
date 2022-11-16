package com.earl.ktorchatapp.domain.models

import com.earl.ktorchatapp.domain.mappers.RegisterDtoDomainToDataMapper
import javax.inject.Inject

interface DomainRegisterDto {

    fun <T> map(mapper: RegisterDtoDomainToDataMapper<T>) : T

    class Base @Inject constructor(
        private val email : String,
        private val username : String,
        private val password : String,
        private val bio : String,
        private val pic : String,
    ) : DomainRegisterDto {
        override fun <T> map(mapper: RegisterDtoDomainToDataMapper<T>) = mapper.map(email, username, password, bio, pic)
    }
}