package com.earl.ktorchatapp.core

interface OperationResultListener {
    fun <T> success(success: T)
    fun fail(exception: Exception?)
}