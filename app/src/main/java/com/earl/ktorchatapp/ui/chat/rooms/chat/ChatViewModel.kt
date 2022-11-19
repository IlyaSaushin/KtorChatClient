package com.earl.ktorchatapp.ui.chat.rooms.chat

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.ktorchatapp.core.Resource
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.mappers.MessageDomainToUiMapper
import com.earl.ktorchatapp.domain.mappers.UserInfoDomainToUiMapper
import com.earl.ktorchatapp.domain.models.DomainMessage
import com.earl.ktorchatapp.ui.mappers.MessageUiToDomainMapper
import com.earl.ktorchatapp.ui.models.UiMessage
import com.earl.ktorchatapp.ui.models.UiUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val interactor: Interactor,
    private val messageDomainToUiMapper: MessageDomainToUiMapper<UiMessage>,
    private val messageUiToDomainMapper: MessageUiToDomainMapper<DomainMessage>,
    private val userInfoDomainToUiMapper: UserInfoDomainToUiMapper<UiUserInfo>
): ViewModel() {

    private val messages: MutableStateFlow<List<UiMessage>> = MutableStateFlow(emptyList())
    val _messages = messages.asStateFlow()
    private val contactDetailsLiveData = MutableLiveData<UiUserInfo>()

    fun initSession(username: String, roomToken: String) {
        fetchMessages(roomToken)
        viewModelScope.launch(Dispatchers.IO) {
            val session = interactor.initChatSocketSession(username, roomToken)
            when (session) {
                is Resource.Success -> {
                    interactor.observeNewMessages()
                        .onEach { message ->
                            messages.value += message.map(messageDomainToUiMapper)
                        }.collect()
                }
                is Resource.Error -> {
                    Log.d("tag", "initSession: error")
                }
            }
        }
    }

    fun initContactDetails(contactName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val details = interactor.fetchContactInfo(contactName).map(userInfoDomainToUiMapper)
            withContext(Dispatchers.Main) {
                contactDetailsLiveData.value = details
            }
        }
    }

    fun observeContactInfo(owner: LifecycleOwner, observer: Observer<UiUserInfo>) {
        contactDetailsLiveData.observe(owner, observer)
    }

    private fun fetchMessages(roomToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val messagesList = interactor.fetchMessagesForRoom(roomToken).map { it.map(messageDomainToUiMapper) }
            withContext(Dispatchers.Main) {
                messages.value = messagesList
            }
        }
    }

    fun sendMessage(message: UiMessage) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.sendMessage(message.map(messageUiToDomainMapper))
        }
    }

    fun closeSession() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.closeSession()
        }
    }
}