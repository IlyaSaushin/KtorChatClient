package com.earl.ktorchatapp.di

import com.earl.ktorchatapp.data.BaseRepository
import com.earl.ktorchatapp.data.mappers.*
import com.earl.ktorchatapp.data.models.DataLoginDto
import com.earl.ktorchatapp.data.models.DataRegisterDto
import com.earl.ktorchatapp.data.models.DataTokenDto
import com.earl.ktorchatapp.data.models.DataUserInfo
import com.earl.ktorchatapp.data.models.remote.RequestLoginDto
import com.earl.ktorchatapp.data.models.remote.RequestRegisterDto
import com.earl.ktorchatapp.data.retrofit.Service
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.Repository
import com.earl.ktorchatapp.domain.WebSocketRepository
import com.earl.ktorchatapp.domain.mappers.LoginDtoDomainToDataMapper
import com.earl.ktorchatapp.domain.mappers.RegisterDtoDomainToDataMapper
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
        loginDataToDomain: LoginDtoDomainToDataMapper<DataLoginDto>,
        tokenRemoteToData: RemoteTokenToDataMapper<DataTokenDto>,
        registerDataToRemoteMapper: RegisterDtoDataToRemoteMapper<RequestRegisterDto>,
        registerDomainToDataMapper: RegisterDtoDomainToDataMapper<DataRegisterDto>,
        loginDtoDataToRemoteMapper: LoginDtoDataToRemoteMapper<RequestLoginDto>,
        userInfoRemoteToDataMapper: UserInfoRemoteToDataMapper<DataUserInfo>,
        userInfoDataToDomainMapper: UserInfoDataToDomainMapper<DomainUserInfo>,
    ) : Repository {
        return BaseRepository(
            service,
            loginDataToDomain,
            tokenRemoteToData,
            registerDataToRemoteMapper,
            registerDomainToDataMapper,
            loginDtoDataToRemoteMapper,
            userInfoRemoteToDataMapper,
            userInfoDataToDomainMapper
        )
    }
}