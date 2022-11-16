package com.earl.ktorchatapp.di

import com.earl.ktorchatapp.data.mappers.*
import com.earl.ktorchatapp.data.models.DataLoginDto
import com.earl.ktorchatapp.data.models.DataRegisterDto
import com.earl.ktorchatapp.data.models.DataTokenDto
import com.earl.ktorchatapp.data.models.remote.RemoteLoginDto
import com.earl.ktorchatapp.data.models.remote.RemoteRegisterDto
import com.earl.ktorchatapp.domain.mappers.BaseLoginUiToDomainMapper
import com.earl.ktorchatapp.domain.mappers.BaseRegisterDtoUIToDomainMapper
import com.earl.ktorchatapp.domain.mappers.LoginDtoDomainToDataMapper
import com.earl.ktorchatapp.domain.mappers.RegisterDtoDomainToDataMapper
import com.earl.ktorchatapp.domain.models.DomainLoginDto
import com.earl.ktorchatapp.domain.models.DomainRegisterDto
import com.earl.ktorchatapp.ui.mappers.LoginDtoUiToDomainMapper
import com.earl.ktorchatapp.ui.mappers.UiRegisterDtoUiToDomainMapper
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
    fun provideRegisterDataToRemoteMapper() : RegisterDtoDataToRemoteMapper<RemoteRegisterDto> {
        return BaseRegisterDataToRemoteMapper()
    }

    @Singleton
    @Provides
    fun provideLoginDtoDataToRemoteMapper() : LoginDtoDataToRemoteMapper<RemoteLoginDto> {
        return BaseLoginDtoDataToRemoteMapper()
    }
}