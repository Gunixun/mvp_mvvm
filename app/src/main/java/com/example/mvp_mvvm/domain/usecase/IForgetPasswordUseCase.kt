package com.example.mvp_mvvm.domain.usecase

import androidx.annotation.MainThread
import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.utils.CallbackData

interface IForgetPasswordUseCase {
    fun forgetPassword(
        email: String,
        @MainThread callback: CallbackData<AccountData>
    )
}