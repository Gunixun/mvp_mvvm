package com.example.mvp_mvvm.model

import androidx.room.*

@Dao
interface AccountDAO {
    @Insert
    fun registration(vararg user: AccountEntry)

    @Update
    fun updateAccount(user: AccountEntry)

    @Query("SELECT * FROM AccountEntry")
    fun getAllAccountData(): List<AccountEntry>
}