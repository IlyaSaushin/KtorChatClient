package com.earl.ktorchatapp.data.mappers

interface ChatRoomDbToDataMapper<T> {

    fun map(
        id: Int,
        roomToken: String,
        contactUsername: String,
        image: String,
    ) : T
}