package com.example.mvp_mvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AccountDTO::class], version = 1)
abstract class AccountsDB : RoomDatabase() {
    abstract fun accountDao(): AccountsDAO
}