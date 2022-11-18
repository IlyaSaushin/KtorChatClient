package com.earl.ktorchatapp.data.mappers

interface RoomCloudToDataMapper <T> {

    fun map(
        id: String,
        name: String,
        private: String
    ) : T
}