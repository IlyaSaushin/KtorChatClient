package com.earl.ktorchatapp.data.models

interface DataTokenDto {

    class Base(
        val token: String
    ) : DataTokenDto {

    }
}