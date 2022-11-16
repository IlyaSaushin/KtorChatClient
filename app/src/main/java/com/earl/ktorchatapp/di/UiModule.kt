package com.earl.ktorchatapp.di

import com.earl.ktorchatapp.ui.register.RegisterDetailsFormValidation
import com.earl.ktorchatapp.ui.register.UserDetailsFormValidation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UiModule {

    @Singleton
    @Provides
    fun provideUserDetailsValidation() : UserDetailsFormValidation {
        return UserDetailsFormValidation.BaseUserDetailsValidation()
    }

    @Singleton
    @Provides
    fun provideRegisterDetailsFormValidation() : RegisterDetailsFormValidation {
        return RegisterDetailsFormValidation.Base()
    }
}