package com.earl.ktorchatapp.ui.chat.rooms

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.mappers.RoomDomainToUiMapper
import com.earl.ktorchatapp.ui.models.UiChatRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomsViewModel @Inject constructor(
    private val interactor: Interactor,
    private val roomDomainToUiMapper: RoomDomainToUiMapper<UiChatRoom>
) : ViewModel() {

    private val roomsListLiveData = MutableLiveData<List<UiChatRoom>>()

    fun fetchRooms(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val roomsList = interactor.fetchRooms(token)
            withContext(Dispatchers.Main) {
                roomsListLiveData.value = roomsList.map { it.map(roomDomainToUiMapper) }
            }
        }
    }

    fun observeRoomsLivedata(owner: LifecycleOwner, observer: Observer<List<UiChatRoom>>) {
        roomsListLiveData.observe(owner, observer)
    }
}