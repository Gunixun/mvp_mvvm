package com.example.mvp_mvvm.domain.use_cases

import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.utils.CallbackData

interface ForgetPasswordUseCase {
    fun forgetPassword(
        email: String,
        callback: CallbackData<Account>
    )
}