package com.example.mvp_mvvm.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AccountsDAO {
    @Insert
    fun registration(vararg user: AccountEntity)

    @Update
    fun updateAccount(user: AccountEntity)

    @Query("SELECT * FROM AccountEntity")
    fun getAllAccountData(): List<AccountEntity>
}