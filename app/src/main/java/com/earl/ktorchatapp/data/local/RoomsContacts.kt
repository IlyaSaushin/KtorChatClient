package com.earl.ktorchatapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.earl.ktorchatapp.data.mappers.ChatRoomDbToDataMapper

@Entity(tableName = "roomsContacts")
data class RoomsContacts(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "token") val roomToken: String,
    @ColumnInfo(name = "contact") val contactUsername: String,
    @ColumnInfo(name = "icon") val image: String,
) {
    fun <T> map(mapper: ChatRoomDbToDataMapper<T>) = mapper.map(id, roomToken, contactUsername, image)
}
