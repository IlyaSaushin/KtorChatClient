package com.earl.ktorchatapp.di

import com.earl.ktorchatapp.data.mappers.*
import com.earl.ktorchatapp.data.models.*
import com.earl.ktorchatapp.data.models.remote.RequestLoginDto
import com.earl.ktorchatapp.data.models.remote.RequestMessageDto
import com.earl.ktorchatapp.data.models.remote.RequestRegisterDto
import com.earl.ktorchatapp.domain.mappers.*
import com.earl.ktorchatapp.domain.models.*
import com.earl.ktorchatapp.ui.mappers.*
import com.earl.ktorchatapp.ui.models.UiChatRoom
import com.earl.ktorchatapp.ui.models.UiMessage
import com.earl.ktorchatapp.ui.models.UiUserInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MappersModule {

    @Singleton
    @Provides
    fun provideLoginDtoDomainToDataMapper() : LoginDtoDomainToDataMapper<DataLoginDto> {
        return BaseLoginDtoDomainToDataMapper()
    }

    @Singleton
    @Provides
    fun provideLoginDtoUiToDomainMapper() : LoginDtoUiToDomainMapper<DomainLoginDto> {
        return BaseLoginUiToDomainMapper()
    }

    @Singleton
    @Provides
    fun provideTokenDtoRemoteToDataMapper() : RemoteTokenToDataMapper<DataTokenDto> {
        return BaseTokenRemoteToDataMapper()
    }

    @Singleton
    @Provides
    fun provideRegisterDtoUiToDomainModule() : UiRegisterDtoUiToDomainMapper<DomainRegisterDto> {
        return BaseRegisterDtoUIToDomainMapper()
    }

    @Singleton
    @Provides
    fun provideRegisterDomainToDataMapper() : RegisterDtoDomainToDataMapper<DataRegisterDto> {
        return BaseRegisterDtoDomainToDataMapper()
    }

    @Provides
    @Singleton
    fun provideRegisterDataToRemoteMapper() : RegisterDtoDataToRemoteMapper<RequestRegisterDto> {
        return BaseRegisterDataToRemoteMapper()
    }

    @Singleton
    @Provides
    fun provideLoginDtoDataToRemoteMapper() : LoginDtoDataToRemoteMapper<RequestLoginDto> {
        return BaseLoginDtoDataToRemoteMapper()
    }

    @Singleton
    @Provides
    fun provideUserInfoRemoteToDataMapper() : UserInfoRemoteToDataMapper<DataUserInfo> {
        return BaseUserInfoRemoteToDataMapper()
    }

    @Singleton
    @Provides
    fun provideUserInfoDataToDomainMapper() : UserInfoDataToDomainMapper<DomainUserInfo> {
        return BaseUserInfoDataToDomainMapper()
    }

    @Singleton
    @Provides
    fun provideUserInfoDomainToUiMapper() : UserInfoDomainToUiMapper<UiUserInfo> {
        return BaseUserInfoDomainToUiMapper()
    }

    @Singleton
    @Provides
    fun provideMessageCloudToDataMapper() : MessageCloudToDataMapper<DataMessage> {
        return BaseMessageCloudToDataMapper()
    }

    @Singleton
    @Provides
    fun provideMessageDataToDomainMapper() : MessageDataToDomainMapper<DomainMessage> {
        return BaseMessageDataToDomainMapper()
    }

    @Singleton
    @Provides
    fun providesMessageDomainToUiMapper() : MessageDomainToUiMapper<UiMessage> {
        return BaseMessageDomainToUiMapper()
    }

    @Singleton
    @Provides
    fun provideMessageUiToDomainMapper() : MessageUiToDomainMapper<DomainMessage> {
        return BaseMessageUiToDomainMapper()
    }

    @Singleton
    @Provides
    fun provideChatRoomCloudToDataMapper() : RoomCloudToDataMapper<DataChatRoom> {
        return BaseRoomCloudToDataMapper()
    }

    @Singleton
    @Provides
    fun provideChatRoomDataToDomainMapper() : RoomDataToDomainMapper<DomainChatRoom> {
        return BaseRoomDataToDomainMapper()
    }

    @Singleton
    @Provides
    fun provideChatRoomDomainToUiMapper() : RoomDomainToUiMapper<UiChatRoom> {
        return BaseRoomDomainToUiMapper()
    }

    @Singleton
    @Provides
    fun provideMessageDomainToDataMapper() : MessageDomainToDataMapper<DataMessage> {
        return BaseMessageDomainToDataMapper()
    }

    @Singleton
    @Provides
    fun provideMessageDataToCloudMapper() : MessageDataToRequestMapper<RequestMessageDto> {
        return BaseMessageDataToRequestMapper()
    }
}