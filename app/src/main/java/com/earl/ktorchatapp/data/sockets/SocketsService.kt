package com.earl.ktorchatapp.data.sockets

interface SocketsService {

    companion object {
        // default
//        const val BASE_URL = "ws://10.0.2.2:8080/"
        // genymotion
        const val BASE_URL = "ws://10.0.3.2:8080/"
    }

    sealed class Endpoints(val url: String) {
        object Contacts: Endpoints("$BASE_URL/socketContacts")
    }
}