package com.example.mvp_mvvm

import android.app.Application
import androidx.room.Room
import com.example.mvp_mvvm.domain.AccountsDAO
import com.example.mvp_mvvm.domain.AccountsDB

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: App? = null
        private var db: AccountsDB? = null

        fun getAccountDao(): AccountsDAO {
            if (db == null) {
                synchronized(AccountsDB::class.java) {
                    if (db == null) {
                        if (instance == null)
                            throw IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            instance!!.applicationContext,
                            AccountsDB::class.java,
                            "Accounts.db"
                        ).build()
                    }
                }
            }

            return db!!.accountDao()
        }
    }
}