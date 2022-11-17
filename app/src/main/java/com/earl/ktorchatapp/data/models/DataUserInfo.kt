package com.earl.ktorchatapp.data.models

import com.earl.ktorchatapp.data.mappers.UserInfoDataToDomainMapper

interface DataUserInfo {

    fun <T> map(mapper: UserInfoDataToDomainMapper<T>) : T

    class Base(
        private val email: String,
        private val username: String,
        private val bio: String,
        private val pic: String
    ) : DataUserInfo {
        override fun <T> map(mapper: UserInfoDataToDomainMapper<T>) = mapper.map(email, username, bio, pic)
    }
}