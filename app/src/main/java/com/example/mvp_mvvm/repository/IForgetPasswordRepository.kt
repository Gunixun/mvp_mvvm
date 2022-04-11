package com.example.mvp_mvvm.repository

import com.example.mvp_mvvm.model.AccountData
import com.example.mvp_mvvm.model.AccountEntity
import com.example.mvp_mvvm.utils.CallbackData

interface IForgetPasswordRepository {

    fun getAllLocalAccount(): List<AccountEntity>

    fun findAccount(email: String, callback: CallbackData<AccountData>)
}