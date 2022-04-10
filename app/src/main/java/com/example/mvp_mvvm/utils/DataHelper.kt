package com.example.mvp_mvvm.utils

import com.example.mvp_mvvm.model.AccountData
import com.example.mvp_mvvm.model.AccountEntity

fun convertAccountEntityToData(account: AccountEntity):AccountData{
    return AccountData(
        uid = account.uid,
        login = account.login,
        email = account.email
    )
}