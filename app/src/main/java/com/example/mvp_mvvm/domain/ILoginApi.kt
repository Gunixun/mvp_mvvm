package com.example.mvp_mvvm.domain

import androidx.annotation.WorkerThread

interface ILoginApi {
    @WorkerThread
    fun getAllAccounts(): List<AccountEntity>

    @WorkerThread
    fun login(login: String, password: String) : AccountEntity

    @WorkerThread
    fun register(login: String, password: String, email: String) : AccountEntity

    @WorkerThread
    fun forgotPassword(email: String) : AccountEntity
}