package com.earl.ktorchatapp.di

import com.earl.ktorchatapp.data.mappers.*
import com.earl.ktorchatapp.data.models.DataLoginDto
import com.earl.ktorchatapp.data.models.DataRegisterDto
import com.earl.ktorchatapp.data.models.DataTokenDto
import com.earl.ktorchatapp.data.models.DataUserInfo
import com.earl.ktorchatapp.data.models.remote.RequestLoginDto
import com.earl.ktorchatapp.data.models.remote.RequestRegisterDto
import com.earl.ktorchatapp.domain.mappers.*
import com.earl.ktorchatapp.domain.models.DomainLoginDto
import com.earl.ktorchatapp.domain.models.DomainRegisterDto
import com.earl.ktorchatapp.domain.models.DomainUserInfo
import com.earl.ktorchatapp.ui.mappers.BaseUserInfoDomainToUiMapper
import com.earl.ktorchatapp.ui.mappers.LoginDtoUiToDomainMapper
import com.earl.ktorchatapp.ui.mappers.UiRegisterDtoUiToDomainMapper
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
}