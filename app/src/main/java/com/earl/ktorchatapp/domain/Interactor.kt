package com.earl.ktorchatapp.domain

import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.domain.models.DomainLoginDto
import com.earl.ktorchatapp.domain.models.DomainRegisterDto
import javax.inject.Inject

interface Interactor {

    suspend fun login(dto: DomainLoginDto, callback: OperationResultListener)

    suspend fun register(dto: DomainRegisterDto, callback: OperationResultListener)

    class Base @Inject constructor(
        private val repository: Repository
    ) : Interactor {

        override suspend fun login(dto: DomainLoginDto, callback: OperationResultListener) {
            repository.login(dto, callback)
        }

        override suspend fun register(dto: DomainRegisterDto, callback: OperationResultListener) {
            repository.register(dto, callback)
        }
    }
}