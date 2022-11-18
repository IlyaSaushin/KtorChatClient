package com.earl.ktorchatapp.ui.chat.contacts

import androidx.lifecycle.*
import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.mappers.UserInfoDomainToUiMapper
import com.earl.ktorchatapp.ui.models.UiUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContactsViewModel @Inject constructor(
    private val interactor: Interactor,
    private val userInfoDomainToUiMapper: UserInfoDomainToUiMapper<UiUserInfo>,
) : ViewModel() {

    private val contactsListLiveData = MutableLiveData<List<UiUserInfo>>()

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

    fun startChat(name: String, private: String, author: String, users: List<String>, callback: OperationResultListener) {
        viewModelScope.launch(Dispatchers.IO) {
            val token = interactor.addRoom(name, private, author, users)
            withContext(Dispatchers.Main) {
                if (callback as? String != "") {
                    callback.success(token)
                } else {
                    callback.fail(Exception("Unknown error"))
                }
            }
        }
    }

    fun observeContactsListLiveData(owner: LifecycleOwner, observer: Observer<List<UiUserInfo>>) {
        contactsListLiveData.observe(owner, observer)
    }
}