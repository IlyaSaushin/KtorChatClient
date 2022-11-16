package com.earl.ktorchatapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.models.DomainLoginDto
import com.earl.ktorchatapp.ui.mappers.LoginDtoUiToDomainMapper
import com.earl.ktorchatapp.ui.models.UiLoginDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragmentViewModel @Inject constructor(
    private val interactor: Interactor,
    private val loginDtoMapper: LoginDtoUiToDomainMapper<DomainLoginDto>
) : ViewModel() {

    fun login(dto: UiLoginDto, callback: OperationResultListener) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.login(dto.map(loginDtoMapper), callback)
        }
    }
}