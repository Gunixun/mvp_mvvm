package com.example.mvp_mvvm.utils

import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.domain.AccountEntity

fun convertAccountEntityToData(account: AccountEntity):AccountData{
    return AccountData(
        uid = account.uid,
        login = account.login,
        email = account.email
    )
}