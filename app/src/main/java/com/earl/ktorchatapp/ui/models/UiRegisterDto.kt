package com.earl.ktorchatapp.ui.models

import com.earl.ktorchatapp.ui.mappers.UiRegisterDtoUiToDomainMapper
import javax.inject.Inject

interface UiRegisterDto {

    fun <T> map(mapper: UiRegisterDtoUiToDomainMapper<T>) : T

    class Base @Inject constructor(
        private val email : String,
        private val username : String,
        private val password : String,
        private val bio : String,
        private val pic : String,
    ) : UiRegisterDto {
        override fun <T> map(mapper: UiRegisterDtoUiToDomainMapper<T>) = mapper.map(email, username, password, bio, pic)
    }
}