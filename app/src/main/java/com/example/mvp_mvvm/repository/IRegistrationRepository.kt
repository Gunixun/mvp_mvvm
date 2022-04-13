package com.example.mvp_mvvm.repository

import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.domain.AccountEntity
import com.example.mvp_mvvm.utils.CallbackData

interface IRegistrationRepository {
    fun getAllLocalAccount(): List<AccountEntity>

    fun registration(
        login: String,
        password: String,
        email: String,
        callback: CallbackData<AccountData>
    )
}