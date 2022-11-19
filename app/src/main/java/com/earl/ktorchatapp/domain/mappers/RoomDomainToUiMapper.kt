package com.earl.ktorchatapp.domain.mappers

interface RoomDomainToUiMapper<T> {

    fun map(
        id: String,
        name: String,
        icon: String
    ) : T
}