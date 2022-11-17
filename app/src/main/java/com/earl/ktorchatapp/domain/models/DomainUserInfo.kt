package com.earl.ktorchatapp.domain.models

import com.earl.ktorchatapp.domain.mappers.UserInfoDomainToUiMapper

interface DomainUserInfo {

    fun <T> map(mapper: UserInfoDomainToUiMapper<T>) : T

    class Base(
        private val email: String,
        private val username: String,
        private val bio: String,
        private val pic: String
    ) : DomainUserInfo {
        override fun <T> map(mapper: UserInfoDomainToUiMapper<T>) = mapper.map(email, username, bio, pic)
    }
}