package com.earl.ktorchatapp.data

import android.util.Log
import com.earl.ktorchatapp.core.Resource
import com.earl.ktorchatapp.data.mappers.*
import com.earl.ktorchatapp.data.models.DataMessage
import com.earl.ktorchatapp.data.models.DataUserInfo
import com.earl.ktorchatapp.data.models.remote.RequestMessageDto
import com.earl.ktorchatapp.data.retrofit.RemoteMessageDto
import com.earl.ktorchatapp.domain.SocketsService
import com.earl.ktorchatapp.domain.WebSocketRepository
import com.earl.ktorchatapp.domain.mappers.MessageDomainToDataMapper
import com.earl.ktorchatapp.domain.models.DomainMessage
import com.earl.ktorchatapp.domain.models.DomainUserInfo
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class BaseSocketRepository @Inject constructor(
    private val client: HttpClient,
    private val userInfoRemoteToDataMapper: UserInfoRemoteToDataMapper<DataUserInfo>,
    private val userInfoDataToDomainMapper: UserInfoDataToDomainMapper<DomainUserInfo>,
    private val messageCloudToDataMapper: MessageCloudToDataMapper<DataMessage>,
    private val messageDataToDomainMapper: MessageDataToDomainMapper<DomainMessage>,
    private val messageDomainToDataMapper: MessageDomainToDataMapper<DataMessage>,
    private val messageDataToRequestMapper: MessageDataToRequestMapper<RequestMessageDto>
) : WebSocketRepository {

    private var socket: WebSocketSession? = null

    override suspend fun initChatSession(username: String, roomToken: String) =
        try {
            socket = client.webSocketSession {
                url("${SocketsService.Endpoints.Chat.url}?username=$username&roomId=$roomToken")
            }
            if (socket?.isActive == true) {
                Resource.Success(Unit)
            } else {
                Resource.Error("Couldn't establish a connection.")
            }
        } catch (e: Exception) {
            Log.d("tag", "initChatSession: $e")
            e.printStackTrace()
            Resource.Error(e.localizedMessage ?: "unknown error")
        }

    override suspend fun sendMessage(message: DomainMessage) {
        try {
            val requestMessage = message
                .mapToData(messageDomainToDataMapper)
                .mapToRequest(messageDataToRequestMapper)
            val messageJson = Json.encodeToString(requestMessage)
            socket?.send(Frame.Text(messageJson))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun observeMessages(): Flow<DomainMessage> {
        return try {
            socket?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map {
                    val json = (it as? Frame.Text)?.readText() ?: "bad msg transcription"
                    val messageDto = Json.decodeFromString<RemoteMessageDto>(json)
                    messageDto
                        .map(messageCloudToDataMapper)
                        .map(messageDataToDomainMapper)
                }!!
        } catch(e: Exception) {
            e.printStackTrace()
            Log.d("tag", "observeMessages: exception -  $e")
            flow {  }
        }
    }

    override suspend fun closeSession() {
        socket?.close()
    }
}
