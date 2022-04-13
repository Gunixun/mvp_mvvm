package com.example.mvp_mvvm.domain.usecase

import androidx.annotation.MainThread
import com.example.mvp_mvvm.domain.entities.AccountEntity
import com.example.mvp_mvvm.utils.CallbackData

interface IForgetPasswordUseCase {
    fun forgetPassword(
        email: String,
        @MainThread callback: CallbackData<AccountEntity>
    )
}