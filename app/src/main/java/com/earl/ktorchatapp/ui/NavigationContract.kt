package com.earl.ktorchatapp.ui

interface NavigationContract {

    fun login()

    fun register()

    fun showAddDetailsFragment(email: String, password: String)

    fun chatBaseFragment()

    fun chat(token: String)

    fun contacts()

    fun showAddNewContactFragment()

    fun back()

    fun showProgressBar()

    fun hideProgressBar()

    fun closeApp()
}