package com.example.mvp_mvvm.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AccountEntity::class], version = 1)
abstract class AccountsDB : RoomDatabase() {
    abstract fun accountDao(): AccountsDAO
}