package com.example.mvp_mvvm.repository

import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.domain.AccountEntity
import com.example.mvp_mvvm.utils.CallbackData

interface IForgetPasswordRepository {

    fun getAllLocalAccount(): List<AccountEntity>

    fun findAccount(email: String, callback: CallbackData<AccountData>)
}