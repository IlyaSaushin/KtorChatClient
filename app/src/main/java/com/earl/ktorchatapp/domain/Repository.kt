package com.earl.ktorchatapp.domain

import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.domain.models.DomainLoginDto
import com.earl.ktorchatapp.domain.models.DomainRegisterDto
import com.earl.ktorchatapp.domain.models.DomainUserInfo

interface Repository {

    suspend fun login(loginDto: DomainLoginDto, callback: OperationResultListener)

    suspend fun register(registerDto: DomainRegisterDto, callback: OperationResultListener)

    suspend fun fetchUserInfo(userToken: String) : DomainUserInfo

    suspend fun fetchContacts(userToken: String) : List<DomainUserInfo>
}