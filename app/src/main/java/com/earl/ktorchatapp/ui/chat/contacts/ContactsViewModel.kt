package com.earl.ktorchatapp.ui.chat.contacts

import androidx.lifecycle.*
import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.mappers.RoomDomainToUiMapper
import com.earl.ktorchatapp.domain.mappers.UserInfoDomainToUiMapper
import com.earl.ktorchatapp.ui.models.UiChatRoom
import com.earl.ktorchatapp.ui.models.UiUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.crypto.spec.PBEKeySpec
import javax.inject.Inject

class ContactsViewModel @Inject constructor(
    private val interactor: Interactor,
    private val userInfoDomainToUiMapper: UserInfoDomainToUiMapper<UiUserInfo>,
    private val roomDomainToUiMapper: RoomDomainToUiMapper<UiChatRoom>
) : ViewModel() {

    private val contactsListLiveData = MutableLiveData<List<UiUserInfo>>()
    private val newRoomTokenLiveData = MutableLiveData<String>()
    private val allRoomsContactLiveData = MutableLiveData<List<UiChatRoom>>()

    fun fetchContacts(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val contactsList = interactor.fetchContacts(token).map {
                it.map(userInfoDomainToUiMapper)
            }
            withContext(Dispatchers.Main) {
                contactsListLiveData.value = contactsList
            }
        }
    }

    fun removeUserFromContacts(userUsername: String, contactUsername: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.removeUserFromContact(userUsername, contactUsername)
        }
    }

    fun startChat(name: String, icon: String, author: String, users: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            val token = interactor.addRoom(name, icon, author, users)
            withContext(Dispatchers.Main) {
                newRoomTokenLiveData.value = token
            }
        }
    }

    fun insertNewRoomContactsIntoDb(roomToken: String, contactName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.insertNewRoomContactsIntoDb(roomToken, contactName)
        }
    }

    fun fetchAllRoomsContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val listContactsNames = interactor.fetchAllRoomsContacts()
            withContext(Dispatchers.Main) {
                allRoomsContactLiveData.value = listContactsNames.map { it.map(roomDomainToUiMapper) }
            }
        }
    }

    fun observeNewRoomTokenLiveData(owner: LifecycleOwner, observer: Observer<String>) {
        newRoomTokenLiveData.observe(owner, observer)
    }

    fun observeAllRoomsContactsLiveData(owner: LifecycleOwner, observer: Observer<List<UiChatRoom>>) {
        allRoomsContactLiveData.observe(owner, observer)
    }

    fun observeContactsListLiveData(owner: LifecycleOwner, observer: Observer<List<UiUserInfo>>) {
        contactsListLiveData.observe(owner, observer)
    }
}