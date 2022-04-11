package com.example.mvp_mvvm.model

import androidx.room.*

@Dao
interface AccountsDAO {
    @Insert
    fun registration(vararg user: AccountEntity)

    @Update
    fun updateAccount(user: AccountEntity)

    @Query("SELECT * FROM AccountEntity")
    fun getAllAccountData(): List<AccountEntity>
}