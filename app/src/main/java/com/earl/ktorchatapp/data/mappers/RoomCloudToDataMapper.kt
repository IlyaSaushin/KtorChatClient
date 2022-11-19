package com.earl.ktorchatapp.data.mappers

interface RoomCloudToDataMapper <T> {

    fun map(
        id: String,
        name: String,
        icon: String
    ) : T
}