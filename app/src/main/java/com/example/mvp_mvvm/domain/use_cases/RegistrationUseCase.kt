package com.example.mvp_mvvm.domain.use_cases

import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.utils.CallbackData

interface RegistrationUseCase {
    fun register(
        login: String,
        password: String,
        email: String,
        callback: CallbackData<Account>
    )
}