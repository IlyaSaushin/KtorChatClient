package com.earl.ktorchatapp.data.models

import com.earl.ktorchatapp.data.mappers.RegisterDtoDataToRemoteMapper
import javax.inject.Inject

interface DataRegisterDto {

    fun <T> map(mapper: RegisterDtoDataToRemoteMapper<T>) : T

    class Base @Inject constructor(
        private val email : String,
        private val username : String,
        private val password : String,
        private val bio : String,
        private val pic : String,
    ) : DataRegisterDto {
        override fun <T> map(mapper: RegisterDtoDataToRemoteMapper<T>) = mapper.map(email, username, password, bio, pic)
    }
}