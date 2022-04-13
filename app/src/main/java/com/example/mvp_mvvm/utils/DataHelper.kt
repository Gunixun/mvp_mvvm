package com.example.mvp_mvvm.utils

import com.example.mvp_mvvm.data.db.AccountDTO
import com.example.mvp_mvvm.domain.entities.AccountEntity

fun convertAccountDtoToEntity(account: AccountDTO): AccountEntity {
    return AccountEntity(
        uid = account.uid,
        login = account.login,
        email = account.email
    )
}