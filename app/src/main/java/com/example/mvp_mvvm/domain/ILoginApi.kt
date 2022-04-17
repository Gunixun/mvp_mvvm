package com.example.mvp_mvvm.domain

import androidx.annotation.WorkerThread
import com.example.mvp_mvvm.domain.entities.Account

interface ILoginApi {

    @WorkerThread
    fun login(login: String, password: String) : Account

    @WorkerThread
    fun register(login: String, password: String, email: String) : Account

    @WorkerThread
    fun forgotPassword(email: String) : Account
}