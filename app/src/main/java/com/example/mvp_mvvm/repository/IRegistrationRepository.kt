package com.example.mvp_mvvm.repository

import com.example.mvp_mvvm.model.AccountData
import com.example.mvp_mvvm.model.AccountEntity
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