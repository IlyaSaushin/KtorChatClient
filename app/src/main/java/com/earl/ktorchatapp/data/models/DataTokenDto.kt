package com.earl.ktorchatapp.data.models

interface DataTokenDto {

    fun token() : String

    class Base(
        val token: String
    ) : DataTokenDto {
        override fun token() = token
    }
}