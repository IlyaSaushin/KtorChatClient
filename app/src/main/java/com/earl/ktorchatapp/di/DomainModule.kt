package com.earl.ktorchatapp.di

import com.earl.ktorchatapp.data.BaseRepository
import com.earl.ktorchatapp.data.local.AppDataBase
import com.earl.ktorchatapp.data.mappers.*
import com.earl.ktorchatapp.data.models.*
import com.earl.ktorchatapp.data.models.remote.RequestLoginDto
import com.earl.ktorchatapp.data.models.remote.RequestRegisterDto
import com.earl.ktorchatapp.data.retrofit.Service
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.Repository
import com.earl.ktorchatapp.domain.WebSocketRepository
import com.earl.ktorchatapp.domain.mappers.BaseMessageDataToDomainMapper
import com.earl.ktorchatapp.domain.mappers.BaseRoomDataToDomainMapper
import com.earl.ktorchatapp.domain.mappers.LoginDtoDomainToDataMapper
import com.earl.ktorchatapp.domain.mappers.RegisterDtoDomainToDataMapper
import com.earl.ktorchatapp.domain.models.DomainChatRoom
import com.earl.ktorchatapp.domain.models.DomainMessage
import com.earl.ktorchatapp.domain.models.DomainUserInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideInteractor(
        repository: Repository,
        webSocketRepository: WebSocketRepository
    ) : Interactor {
        return Interactor.Base(
            repository,
            webSocketRepository
        )
    }

//    @Singleton
//    @Binds
//    abstract fun bindInteractor(base: Interactor.Base) : Interactor
//
//    @Singleton
//    @Binds
//    abstract fun bindRepository(base: BaseRepository) : Repository

    @Singleton
    @Provides
    fun provideRepository(
        service: Service,
        appDataBase: AppDataBase,
        loginDataToDomain: LoginDtoDomainToDataMapper<DataLoginDto>,
        tokenRemoteToData: RemoteTokenToDataMapper<DataTokenDto>,
        registerDataToRemoteMapper: RegisterDtoDataToRemoteMapper<RequestRegisterDto>,
        registerDomainToDataMapper: RegisterDtoDomainToDataMapper<DataRegisterDto>,
        loginDtoDataToRemoteMapper: LoginDtoDataToRemoteMapper<RequestLoginDto>,
        userInfoRemoteToDataMapper: UserInfoRemoteToDataMapper<DataUserInfo>,
        userInfoDataToDomainMapper: UserInfoDataToDomainMapper<DomainUserInfo>,
        messageCloudToDataMapper: MessageCloudToDataMapper<DataMessage>,
        messageDataToDomainMapper: MessageDataToDomainMapper<DomainMessage>,
        roomCloudToDataMapper: RoomCloudToDataMapper<DataChatRoom>,
        roomDataToDomainMapper: RoomDataToDomainMapper<DomainChatRoom>,
        roomDbToDataMapper: ChatRoomDbToDataMapper<DataChatRoom>,
    ) : Repository {
        return BaseRepository(
            service,
            appDataBase,
            loginDataToDomain,
            tokenRemoteToData,
            registerDataToRemoteMapper,
            registerDomainToDataMapper,
            loginDtoDataToRemoteMapper,
            userInfoRemoteToDataMapper,
            userInfoDataToDomainMapper,
            messageCloudToDataMapper,
            messageDataToDomainMapper,
            roomCloudToDataMapper,
            roomDataToDomainMapper,
            roomDbToDataMapper
        )
    }
}