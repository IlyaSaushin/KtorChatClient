package com.earl.ktorchatapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContact(room: RoomsContacts)

    @Query("select * from roomsContacts")
    suspend fun fetchAllRoomsContacts() : List<RoomsContacts>
}