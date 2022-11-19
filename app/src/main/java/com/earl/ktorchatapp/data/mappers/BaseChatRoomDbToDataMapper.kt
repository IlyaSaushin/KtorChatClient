package com.earl.ktorchatapp.data.mappers

import com.earl.ktorchatapp.data.models.DataChatRoom
import javax.inject.Inject

class BaseChatRoomDbToDataMapper @Inject constructor(): ChatRoomDbToDataMapper<DataChatRoom> {

    override fun map(
        id: Int,
        roomToken: String,
        contactUsername: String,
        image: String
    ) = DataChatRoom.Base(roomToken, contactUsername, image)
}