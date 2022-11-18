package com.earl.ktorchatapp.ui.chat.contacts.addContacts

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.mappers.UserInfoDomainToUiMapper
import com.earl.ktorchatapp.ui.models.UiUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddContactViewModel @Inject constructor(
    private val interactor: Interactor,
    private val userInfoDomainToUiMapper: UserInfoDomainToUiMapper<UiUserInfo>
) : ViewModel() {

    private val usersListLiveData = MutableLiveData<List<UiUserInfo>>()

    fun fetchAllUsers(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = interactor.fetchAllUser(username).map { it.map(userInfoDomainToUiMapper) }
            withContext(Dispatchers.Main) {
                usersListLiveData.value = list
            }
        }
    }

    fun addUserToContacts(userUsername: String, contactUsername: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.addUserToContacts(userUsername, contactUsername)
        }
    }

    fun observeUsersListLiveData(owner: LifecycleOwner, observer: Observer<List<UiUserInfo>>) {
        usersListLiveData.observe(owner, observer)
    }
}