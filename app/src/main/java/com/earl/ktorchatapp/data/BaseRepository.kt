package com.earl.ktorchatapp.data

import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.data.mappers.LoginDtoDataToRemoteMapper
import com.earl.ktorchatapp.data.mappers.RegisterDtoDataToRemoteMapper
import com.earl.ktorchatapp.data.mappers.RemoteTokenToDataMapper
import com.earl.ktorchatapp.data.models.DataLoginDto
import com.earl.ktorchatapp.data.models.DataRegisterDto
import com.earl.ktorchatapp.data.models.DataTokenDto
import com.earl.ktorchatapp.data.models.remote.RemoteLoginDto
import com.earl.ktorchatapp.data.models.remote.RemoteRegisterDto
import com.earl.ktorchatapp.data.retrofit.Service
import com.earl.ktorchatapp.domain.Repository
import com.earl.ktorchatapp.domain.mappers.LoginDtoDomainToDataMapper
import com.earl.ktorchatapp.domain.mappers.RegisterDtoDomainToDataMapper
import com.earl.ktorchatapp.domain.models.DomainLoginDto
import com.earl.ktorchatapp.domain.models.DomainRegisterDto
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val service: Service,
    private val loginDomainToData: LoginDtoDomainToDataMapper<DataLoginDto>,
    private val tokenRemoteToDataMapper: RemoteTokenToDataMapper<DataTokenDto>,
    private val registerDataToRemoteMapper: RegisterDtoDataToRemoteMapper<RemoteRegisterDto>,
    private val registerDomainToDataMapper: RegisterDtoDomainToDataMapper<DataRegisterDto>,
    private val loginDtoDataToRemoteMapper: LoginDtoDataToRemoteMapper<RemoteLoginDto>,
) : Repository {

    override suspend fun login(loginDto: DomainLoginDto, callback: OperationResultListener) {
        try {
            val token = service.login(loginDto.map(loginDomainToData).map(loginDtoDataToRemoteMapper)).map(tokenRemoteToDataMapper)
            callback.success(token)
        } catch (e: Exception) {
            e.printStackTrace()
            callback.fail(e)
        }
    }

    override suspend fun register(
        registerDto: DomainRegisterDto,
        callback: OperationResultListener
    ) {
        try {
            val token = service.register(registerDto.map(registerDomainToDataMapper).map(registerDataToRemoteMapper))
            callback.success(token)
        } catch (e: Exception) {
            e.printStackTrace()
            callback.fail(e)
        }
    }
}