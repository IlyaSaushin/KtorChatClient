package com.earl.ktorchatapp.di

import android.app.Application
import com.earl.ktorchatapp.data.retrofit.Client
import com.earl.ktorchatapp.ui.login.LoginFragment
import com.earl.ktorchatapp.ui.register.RegisterDetailsFragment
import com.earl.ktorchatapp.ui.register.RegisterFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AuthorizationModule::class,
        MappersModule::class,
        Client::class,
        UiModule::class,
        ViewModelsModule::class,
        DomainModule::class,
        Client::class
    ]
)
interface AppComponent {

    fun injectLoginFragment(fragment: LoginFragment)

    fun injectRegisterFragment(fragment: RegisterFragment)

    fun injectRegisterDetailsFragment(fragment: RegisterDetailsFragment)

    fun injectUserDetailsFragment(fragment: RegisterDetailsFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}