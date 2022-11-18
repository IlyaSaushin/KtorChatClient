package com.earl.ktorchatapp.data

import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.data.mappers.*
import com.earl.ktorchatapp.data.models.DataLoginDto
import com.earl.ktorchatapp.data.models.DataRegisterDto
import com.earl.ktorchatapp.data.models.DataTokenDto
import com.earl.ktorchatapp.data.models.DataUserInfo
import com.earl.ktorchatapp.data.models.remote.*
import com.earl.ktorchatapp.data.retrofit.Service
import com.earl.ktorchatapp.domain.Repository
import com.earl.ktorchatapp.domain.mappers.LoginDtoDomainToDataMapper
import com.earl.ktorchatapp.domain.mappers.RegisterDtoDomainToDataMapper
import com.earl.ktorchatapp.domain.models.DomainLoginDto
import com.earl.ktorchatapp.domain.models.DomainRegisterDto
import com.earl.ktorchatapp.domain.models.DomainUserInfo
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val service: Service,
    private val loginDomainToData: LoginDtoDomainToDataMapper<DataLoginDto>,
    private val tokenRemoteToDataMapper: RemoteTokenToDataMapper<DataTokenDto>,
    private val registerDataToRemoteMapper: RegisterDtoDataToRemoteMapper<RequestRegisterDto>,
    private val registerDomainToDataMapper: RegisterDtoDomainToDataMapper<DataRegisterDto>,
    private val loginDtoDataToRemoteMapper: LoginDtoDataToRemoteMapper<RequestLoginDto>,
    private val userInfoRemoteToDataMapper: UserInfoRemoteToDataMapper<DataUserInfo>,
    private val userInfoDataToDomainMapper: UserInfoDataToDomainMapper<DomainUserInfo>,
) : Repository {

    override suspend fun login(loginDto: DomainLoginDto, callback: OperationResultListener) {
        try {
            val token = service.login(
                loginDto.map(loginDomainToData)
                    .map(loginDtoDataToRemoteMapper)
            ).userToken
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
            val token = service.register(
                registerDto
                    .map(registerDomainToDataMapper).map(registerDataToRemoteMapper)
            ).userToken
            callback.success(token)
        } catch (e: Exception) {
            e.printStackTrace()
            callback.fail(e)
        }
    }

    override suspend fun fetchUserInfo(userToken: String) =
        service.fetchUserInfo(RequestTokenDto(userToken))
            .map(userInfoRemoteToDataMapper)
            .map(userInfoDataToDomainMapper)

    override suspend fun fetchContacts(userToken: String) =
        service.fetchContacts(RequestTokenDto(userToken)).map { remote ->
            remote.map(userInfoRemoteToDataMapper)
                .map(userInfoDataToDomainMapper)
        }

    override suspend fun fetchAllUsers(username: String) =
        service.fetchUsersList(RequestUsernameDto(username))
            .map { it.map(userInfoRemoteToDataMapper) }
            .map { it.map(userInfoDataToDomainMapper) }

    override suspend fun addUserToContacts(userUsername: String, contactUsername: String) {
        service.addContact(RequestAddContactDto(userUsername, contactUsername))
    }
}