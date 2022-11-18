package com.earl.ktorchatapp.data.models

import com.earl.ktorchatapp.data.mappers.RoomDataToDomainMapper

interface DataChatRoom {

    fun <T> map(mapper: RoomDataToDomainMapper<T>) : T

    class Base(
        private val id: String,
        private val name: String,
        private val private: String
    ) : DataChatRoom {
        override fun <T> map(mapper: RoomDataToDomainMapper<T>) = mapper.map(id, name, private)
    }
}