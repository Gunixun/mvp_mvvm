package com.example.mvp_mvvm

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mvp_mvvm.data.RoomLoginApi
import com.example.mvp_mvvm.data.use_cases.ForgetPasswordDataSource
import com.example.mvp_mvvm.data.use_cases.LoginDataSource
import com.example.mvp_mvvm.data.use_cases.RegistrationDataSource
import com.example.mvp_mvvm.data.db.AccountsDao
import com.example.mvp_mvvm.data.db.AccountsDb
import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.use_cases.ForgetPasswordUseCase
import com.example.mvp_mvvm.domain.use_cases.LoginUseCase
import com.example.mvp_mvvm.domain.use_cases.RegistrationUseCase

class App : Application() {
    private val loginApi: ILoginApi by lazy { RoomLoginApi(getAccountDao()) }

    val loginDataSource: LoginUseCase by lazy {
        LoginDataSource(app.loginApi)
    }
    val registrationDataSource: RegistrationUseCase by lazy {
        RegistrationDataSource(app.loginApi)
    }
    val forgetPasswordDataSource: ForgetPasswordUseCase by lazy {
        ForgetPasswordDataSource(app.loginApi)
    }

    override fun onCreate() {
        super.onCreate()
        db = Room
            .databaseBuilder(this, AccountsDb::class.java, "Accounts.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        private var db: AccountsDb? = null

        fun getAccountDao(): AccountsDao {
            return db!!.accountDao()
        }
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }