package com.example.mvp_mvvm.repository

import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.domain.AccountEntity
import com.example.mvp_mvvm.utils.CallbackData

interface ILoginRepository {

    fun getAllLocalAccount(): List<AccountEntity>

    fun getAuthorization(login: String, password: String, callback: CallbackData<AccountData>)
}