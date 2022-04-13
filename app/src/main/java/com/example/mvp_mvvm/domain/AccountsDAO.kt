package com.example.mvp_mvvm.domain

import androidx.room.*

@Dao
interface AccountsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registration(entity: AccountEntity)

    @Update
    fun updateAccount(user: AccountEntity)

    @Query("SELECT * FROM AccountEntity")
    fun getAllAccountData(): List<AccountEntity>
}