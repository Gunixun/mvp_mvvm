package com.example.mvp_mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountEntry(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "LOGIN")
    val login: String,
    @ColumnInfo(name = "PASSWORD")
    val password: String,
    @ColumnInfo(name = "EMAIL")
    val email: String
)