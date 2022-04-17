package com.example.mvp_mvvm.utils

import com.example.mvp_mvvm.data.db.AccountEntity
import com.example.mvp_mvvm.domain.entities.Account

fun convertAccountDtoToEntity(account: AccountEntity): Account {
    return Account(
        uid = account.uid,
        login = account.login,
        email = account.email
    )
}