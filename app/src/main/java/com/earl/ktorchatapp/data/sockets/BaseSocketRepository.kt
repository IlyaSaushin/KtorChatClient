package com.earl.ktorchatapp.data.sockets

import com.earl.ktorchatapp.data.mappers.UserInfoDataToDomainMapper
import com.earl.ktorchatapp.data.mappers.UserInfoRemoteToDataMapper
import com.earl.ktorchatapp.data.models.DataUserInfo
import com.earl.ktorchatapp.domain.WebSocketRepository
import com.earl.ktorchatapp.domain.models.DomainUserInfo
import io.ktor.client.*
import io.ktor.websocket.*
import javax.inject.Inject

class BaseSocketRepository @Inject constructor(
    private val client: HttpClient,
    private val userInfoRemoteToDataMapper: UserInfoRemoteToDataMapper<DataUserInfo>,
    private val userInfoDataToDomainMapper: UserInfoDataToDomainMapper<DomainUserInfo>
) : WebSocketRepository {

    private var socket: WebSocketSession? = null

//    override suspend fun initContactsSession(username: String) : Resource<Unit> {
//        return try {
//            socket = client.webSocketSession {
//                url("${SocketsService.Endpoints.Contacts.url}?username=$username")
//            }
//            if(socket?.isActive == true) {
//                Resource.Success(Unit)
//            } else {
//                Resource.Error("Couldn't establish a connection.")
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Resource.Error(e.localizedMessage ?: "unknown error")
//        }
//    }
//
//    override suspend fun observeContacts() : Flow<DomainUserInfo> {
//        return try {
//            socket?.incoming
//                ?.receiveAsFlow()
//                ?.filter { it is Frame.Text }
//                ?.map {
//                    val json = (it as? Frame.Text)?.readText() ?: "bad msg transcription"
//                    val userInfoDto = Json.decodeFromString<RemoteUserInfo>(json)
//                    userInfoDto.map(userInfoRemoteToDataMapper).map(userInfoDataToDomainMapper)
//                }!!
//        } catch (e: Exception) {
//            e.printStackTrace()
//            flow {  }
//        }
//    }
//
//    override suspend fun closeSession() {
//        socket?.close()
//    }
}
