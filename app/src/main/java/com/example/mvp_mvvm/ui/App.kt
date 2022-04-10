package com.example.mvp_mvvm.ui

import android.app.Application
import androidx.room.Room
import com.example.mvp_mvvm.model.AccountDAO
import com.example.mvp_mvvm.model.AccountsDB

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: App? = null
        private var db: AccountsDB? = null

        fun getHistoryDao(): AccountDAO {
            if (db == null) {
                synchronized(AccountsDB::class.java) {
                    if (db == null) {
                        if (instance == null)
                            throw IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            instance!!.applicationContext,
                            AccountsDB::class.java,
                            "History.db"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return db!!.accountDao()
        }
    }
}