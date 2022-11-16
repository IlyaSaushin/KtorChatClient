package com.earl.ktorchatapp.di

import androidx.lifecycle.ViewModel
import com.earl.ktorchatapp.ui.login.LoginFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentViewModel::class)
    abstract fun bindMainViewModel(viewModel: LoginFragmentViewModel): ViewModel
}