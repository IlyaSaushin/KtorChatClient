package com.earl.ktorchatapp.data.mappers

interface RemoteTokenToDataMapper<T> {

    fun map(token: String) : T
}