package com.example.mvp_mvvm.domain.use_cases

import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.utils.CallbackData

interface LoginUseCase {
    fun login(
        login: String,
        password: String,
        callback: CallbackData<Account>
    )
}