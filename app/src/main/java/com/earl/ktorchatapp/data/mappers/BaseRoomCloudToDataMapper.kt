package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.DataChatRoom
import javax.inject.Inject

class BaseRoomCloudToDataMapper @Inject constructor() : RoomCloudToDataMapper<DataChatRoom> {

    override fun map(id: String, name: String, icon: String) = DataChatRoom.Base(id, name, icon)
}