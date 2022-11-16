package com.earl.ktorchatapp.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.models.DomainRegisterDto
import com.earl.ktorchatapp.ui.mappers.UiRegisterDtoUiToDomainMapper
import com.earl.ktorchatapp.ui.models.UiRegisterDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val interactor: Interactor,
    private val registerDtoUiToDomainMapper: UiRegisterDtoUiToDomainMapper<DomainRegisterDto>
) : ViewModel() {

    fun register(dto: UiRegisterDto, callback: OperationResultListener) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.register(dto.map(registerDtoUiToDomainMapper), callback)
        }
    }
}