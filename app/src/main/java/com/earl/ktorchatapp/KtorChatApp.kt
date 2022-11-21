package com.earl.ktorchatapp

import android.app.Application
import com.earl.ktorchatapp.core.SharedPreferenceManager
import com.earl.ktorchatapp.di.DaggerAppComponent
import com.onesignal.OneSignal
import java.util.UUID

class KtorChatApp : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        val uuid = UUID.randomUUID().toString()
        OneSignal.setExternalUserId(uuid)
        OneSignal.promptForPushNotifications()
    }

    companion object {

        private const val ONESIGNAL_APP_ID = "52960a51-4ac6-453b-900c-5ac9c70005e5"
    }
}