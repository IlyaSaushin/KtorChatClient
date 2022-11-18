package com.earl.ktorchatapp.ui.mappers

import com.earl.ktorchatapp.domain.mappers.RoomDomainToUiMapper
import com.earl.ktorchatapp.ui.models.UiChatRoom
import javax.inject.Inject

class BaseRoomDomainToUiMapper @Inject constructor() : RoomDomainToUiMapper<UiChatRoom> {

    override fun map(id: String, name: String, private: String) = UiChatRoom.Base(id, name, private)
}