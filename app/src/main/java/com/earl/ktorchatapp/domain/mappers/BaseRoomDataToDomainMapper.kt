package com.earl.ktorchatapp.domain.mappers

import com.earl.ktorchatapp.data.mappers.RoomDataToDomainMapper
import com.earl.ktorchatapp.domain.models.DomainChatRoom
import javax.inject.Inject

class BaseRoomDataToDomainMapper @Inject constructor(): RoomDataToDomainMapper<DomainChatRoom> {

    override fun map(id: String, name: String, icon: String) = DomainChatRoom.Base(id, name, icon)
}