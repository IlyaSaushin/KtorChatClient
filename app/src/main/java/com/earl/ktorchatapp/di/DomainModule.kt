package com.earl.ktorchatapp.di

import com.earl.ktorchatapp.data.BaseRepository
import com.earl.ktorchatapp.data.mappers.BaseRegisterDataToRemoteMapper
import com.earl.ktorchatapp.data.mappers.LoginDtoDataToRemoteMapper
import com.earl.ktorchatapp.data.mappers.RegisterDtoDataToRemoteMapper
import com.earl.ktorchatapp.data.mappers.RemoteTokenToDataMapper
import com.earl.ktorchatapp.data.models.DataLoginDto
import com.earl.ktorchatapp.data.models.DataRegisterDto
import com.earl.ktorchatapp.data.models.DataTokenDto
import com.earl.ktorchatapp.data.models.remote.RemoteLoginDto
import com.earl.ktorchatapp.data.models.remote.RemoteRegisterDto
import com.earl.ktorchatapp.data.retrofit.Service
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.Repository
import com.earl.ktorchatapp.domain.mappers.LoginDtoDomainToDataMapper
import com.earl.ktorchatapp.domain.mappers.RegisterDtoDomainToDataMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideInteractor(
        repository: Repository
    ) : Interactor {
        return Interactor.Base(repository)
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
        registerDataToRemoteMapper: RegisterDtoDataToRemoteMapper<RemoteRegisterDto>,
        registerDomainToDataMapper: RegisterDtoDomainToDataMapper<DataRegisterDto>,
        loginDtoDataToRemoteMapper: LoginDtoDataToRemoteMapper<RemoteLoginDto>
    ) : Repository {
        return BaseRepository(
            service,
            loginDataToDomain,
            tokenRemoteToData,
            registerDataToRemoteMapper,
            registerDomainToDataMapper,
            loginDtoDataToRemoteMapper
        )
    }
}