package com.earl.ktorchatapp.ui.chat.contacts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.core.Resource
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.mappers.UserInfoDomainToUiMapper
import com.earl.ktorchatapp.ui.models.UiUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContactsViewModel @Inject constructor(
    private val interactor: Interactor,
    private val userInfoDomainToUiMapper: UserInfoDomainToUiMapper<UiUserInfo>,
) : ViewModel() {

    private val contacts: MutableStateFlow<List<UiUserInfo>> = MutableStateFlow(emptyList())
    val _contacts = contacts.asStateFlow()

    fun initSession(username: String, token: String) {
        fetchContacts(token)
        viewModelScope.launch(Dispatchers.IO) {
            when (interactor.initContactsSession(username)) {
                is Resource.Success -> {
                    interactor.observeNewContacts().onEach { info ->
                        info.map(userInfoDomainToUiMapper)
                        contacts.value += info as UiUserInfo
                    }.collect()
                }
                is Resource.Error -> {
                    Log.d("tag", "initSession: error")
                }
            }
        }
    }

    fun fetchContacts(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val contactsList = interactor.fetchContacts(token).map {
                it.map(userInfoDomainToUiMapper)
            }
            withContext(Dispatchers.Main) {
                contacts.value = contactsList
            }
        }
    }

    fun removeUserFromContacts(userUsername: String, contactUsername: String) {
        // todo
    }

    fun closeSession() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.closeContactsSession()
        }
    }
}