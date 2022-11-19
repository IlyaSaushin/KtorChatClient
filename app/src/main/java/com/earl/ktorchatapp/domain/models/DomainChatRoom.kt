package com.earl.ktorchatapp.domain.models

import com.earl.ktorchatapp.domain.mappers.RoomDomainToUiMapper

interface DomainChatRoom {

    fun <T> map(mapper: RoomDomainToUiMapper<T>) : T

    class Base(
        private val id: String,
        private val name: String,
        private val icon: String
    ) : DomainChatRoom {
        override fun <T> map(mapper: RoomDomainToUiMapper<T>) = mapper.map(id, name, icon)
    }
}