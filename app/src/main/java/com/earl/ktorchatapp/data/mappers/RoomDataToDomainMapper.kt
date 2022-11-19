package com.earl.ktorchatapp.data.mappers

interface RoomDataToDomainMapper<T> {

    fun map(
        id: String,
        name: String,
        icon: String
    ) : T
}