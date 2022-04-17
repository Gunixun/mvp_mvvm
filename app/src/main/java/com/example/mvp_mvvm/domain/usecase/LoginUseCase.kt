package com.example.mvp_mvvm.domain.usecase

import androidx.annotation.MainThread
import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.utils.CallbackData

interface LoginUseCase {
    fun login(
        login: String,
        password: String,
        @MainThread callback: CallbackData<Account>
    )
}