package com.earl.ktorchatapp

import android.app.Application
import com.earl.ktorchatapp.di.DaggerAppComponent

class KtorChatApp : Application() {

//    val appComponent = DaggerAppComponent.create()

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()

    }
}