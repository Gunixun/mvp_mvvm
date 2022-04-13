package com.example.mvp_mvvm.data.db

import androidx.room.*

@Dao
interface AccountsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registration(entity: AccountDTO)

    @Update
    fun updateAccount(user: AccountDTO)

    @Query("SELECT * FROM AccountDTO")
    fun getAllAccountData(): List<AccountDTO>
}