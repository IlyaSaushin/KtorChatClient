package com.earl.ktorchatapp.ui.chat.baseFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.ktorchatapp.core.OperationResultListener
import com.earl.ktorchatapp.domain.Interactor
import com.earl.ktorchatapp.domain.mappers.UserInfoDomainToUiMapper
import com.earl.ktorchatapp.ui.models.UiUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatBaseViewModel @Inject constructor(
    private val interactor: Interactor,
    private val userInfoDomainToUiMapper: UserInfoDomainToUiMapper<UiUserInfo>,
) : ViewModel() {

    fun fetchUserInfo(token: String, callback: OperationResultListener) {
        viewModelScope.launch {
            val userInfo = interactor.fetchUserInfo(token).map(userInfoDomainToUiMapper)
            withContext(Dispatchers.Main) {
                if (userInfo != null) {
                    callback.success(userInfo)
                } else {
                    callback.fail(exception = null)
                }
            }
        }
    }
}