package com.earl.ktorchatapp.ui.chat.rooms.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.ktorchatapp.core.Resource
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.mappers.MessageDomainToUiMapper
import com.earl.ktorchatapp.domain.models.DomainMessage
import com.earl.ktorchatapp.ui.mappers.MessageUiToDomainMapper
import com.earl.ktorchatapp.ui.models.UiMessage
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
    private val messageUiToDomainMapper: MessageUiToDomainMapper<DomainMessage>
): ViewModel() {

    private val messages: MutableStateFlow<List<UiMessage>> = MutableStateFlow(emptyList())
    val _messages = messages.asStateFlow()

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
                    Log.d("tag", "initSession: erorr")
                }
            }
        }
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

    suspend fun closeSession() {
        interactor.closeSession()
    }
}