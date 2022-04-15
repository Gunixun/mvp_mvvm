package com.example.mvp_mvvm.domain

import androidx.annotation.WorkerThread
import com.example.mvp_mvvm.domain.entities.AccountEntity

interface ILoginApi {

    @WorkerThread
    fun login(login: String, password: String) : AccountEntity

    @WorkerThread
    fun register(login: String, password: String, email: String) : AccountEntity

    @WorkerThread
    fun forgotPassword(email: String) : AccountEntity
}