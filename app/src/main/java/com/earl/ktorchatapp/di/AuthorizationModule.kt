package com.earl.ktorchatapp.di

import com.earl.ktorchatapp.ui.login.LoginFormValidation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthorizationModule {

    @Singleton
    @Provides
    fun provideLoginAuthForm() : LoginFormValidation {
        return LoginFormValidation.BaseValidation()
    }
}