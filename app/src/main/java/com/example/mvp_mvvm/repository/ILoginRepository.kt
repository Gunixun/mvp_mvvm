package com.example.mvp_mvvm.repository

import com.example.mvp_mvvm.model.AccountData
import com.example.mvp_mvvm.model.AccountEntity
import com.example.mvp_mvvm.utils.CallbackData

interface ILoginRepository {

    fun getAllLocalAccount(): List<AccountEntity>

    fun getAuthorization(login: String, password: String, callback: CallbackData<AccountData>)
}