package com.earl.ktorchatapp.di

import com.earl.ktorchatapp.data.mappers.*
import com.earl.ktorchatapp.data.models.DataMessage
import com.earl.ktorchatapp.data.models.DataUserInfo
import com.earl.ktorchatapp.data.models.remote.RequestMessageDto
import com.earl.ktorchatapp.data.BaseSocketRepository
import com.earl.ktorchatapp.domain.WebSocketRepository
import com.earl.ktorchatapp.domain.mappers.MessageDomainToDataMapper
import com.earl.ktorchatapp.domain.models.DomainMessage
import com.earl.ktorchatapp.domain.models.DomainUserInfo
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
            /* install(JsonPlugin) {
                 serializer<KotlinxSerializer>()
             }*/
        }
    }

//    @Provides
//    @Singleton
//    fun provideNotificationHttpClient() : HttpClient {
//        return HttpClient(Android)
//    }

    @Provides
    @Singleton
    fun provideSocketRepository(
        client: HttpClient,
        userInfoRemoteToDataMapper: UserInfoRemoteToDataMapper<DataUserInfo>,
        userInfoDataToDomainMapper: UserInfoDataToDomainMapper<DomainUserInfo>,
        messageCloudToDataMapper: MessageCloudToDataMapper<DataMessage>,
        messageDataToDomainMapper: MessageDataToDomainMapper<DomainMessage>,
        messageDomainToDataMapper: MessageDomainToDataMapper<DataMessage>,
        messageDataToRequestMapper: MessageDataToRequestMapper<RequestMessageDto>
    ): WebSocketRepository {
        return BaseSocketRepository(
            client,
            userInfoRemoteToDataMapper,
            userInfoDataToDomainMapper,
            messageCloudToDataMapper,
            messageDataToDomainMapper,
            messageDomainToDataMapper,
            messageDataToRequestMapper
        )
    }
}

