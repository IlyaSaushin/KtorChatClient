package com.earl.ktorchatapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Database(entities = [RoomsContacts::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun roomsDao() : RoomsDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun localDataBase(context: Context) : AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "dataBase"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}