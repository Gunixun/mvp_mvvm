package com.example.mvp_mvvm.data.db

import androidx.room.*

@Dao
interface AccountsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registration(entity: AccountEntity)

    @Update
    fun updateAccount(user: AccountEntity)

    @Query("SELECT * FROM AccountEntity")
    fun getAllAccountData(): List<AccountEntity>
}