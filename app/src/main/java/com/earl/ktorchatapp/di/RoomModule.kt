package com.earl.ktorchatapp.di

import android.app.Application
import com.earl.ktorchatapp.data.local.AppDataBase
import com.earl.ktorchatapp.data.local.RoomsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDataBase(application: Application) : AppDataBase {
        return AppDataBase.localDataBase(application)
    }

    @Singleton
    @Provides
    fun provideRoomsContactsDao(
        application: Application
    ): RoomsDao {
        return AppDataBase.localDataBase(application).roomsDao()
    }
}