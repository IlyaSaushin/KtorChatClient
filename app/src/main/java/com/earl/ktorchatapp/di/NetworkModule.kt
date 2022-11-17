package com.earl.ktorchatapp.di

import com.earl.ktorchatapp.data.mappers.UserInfoDataToDomainMapper
import com.earl.ktorchatapp.data.mappers.UserInfoRemoteToDataMapper
import com.earl.ktorchatapp.data.models.DataUserInfo
import com.earl.ktorchatapp.data.sockets.BaseSocketRepository
import com.earl.ktorchatapp.domain.WebSocketRepository
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

    @Provides
    @Singleton
    fun provideSocketRepository(
        client: HttpClient,
        userInfoRemoteToDataMapper: UserInfoRemoteToDataMapper<DataUserInfo>,
        userInfoDataToDomainMapper: UserInfoDataToDomainMapper<DomainUserInfo>
    ): WebSocketRepository {
        return BaseSocketRepository(
            client,
            userInfoRemoteToDataMapper,
            userInfoDataToDomainMapper
        )
    }
}

