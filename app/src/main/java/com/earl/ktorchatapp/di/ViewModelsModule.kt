package com.earl.ktorchatapp.di

import androidx.lifecycle.ViewModel
import com.earl.ktorchatapp.ui.chat.baseFragment.ChatBaseViewModel
import com.earl.ktorchatapp.ui.chat.contacts.ContactsViewModel
import com.earl.ktorchatapp.ui.chat.contacts.addContacts.AddContactViewModel
import com.earl.ktorchatapp.ui.chat.rooms.RoomsViewModel
import com.earl.ktorchatapp.ui.chat.rooms.chat.ChatViewModel
import com.earl.ktorchatapp.ui.login.LoginFragmentViewModel
import com.earl.ktorchatapp.ui.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginFragmentViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(viewModel: RegisterViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatBaseViewModel::class)
    abstract fun bindChatBaseViewModel(viewModel: ChatBaseViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactsViewModel::class)
    abstract fun bindContactsViewModel(viewModel: ContactsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddContactViewModel::class)
    abstract fun bindAddContactViewModel(viewModel: AddContactViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoomsViewModel::class)
    abstract fun bindRoomsViewModel(viewModel: RoomsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun bindChatViewModel(viewModel: ChatViewModel) : ViewModel
}