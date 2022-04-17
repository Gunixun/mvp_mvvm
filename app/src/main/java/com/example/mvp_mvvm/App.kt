package com.example.mvp_mvvm

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.room.Room
import com.example.mvp_mvvm.data.RoomLoginApi
import com.example.mvp_mvvm.data.use_case.ForgetPasswordDataSource
import com.example.mvp_mvvm.data.use_case.LoginDataSource
import com.example.mvp_mvvm.data.use_case.RegistrationDataSource
import com.example.mvp_mvvm.data.db.AccountsDao
import com.example.mvp_mvvm.data.db.AccountsDb
import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.usecase.ForgetPasswordUseCase
import com.example.mvp_mvvm.domain.usecase.LoginUseCase
import com.example.mvp_mvvm.domain.usecase.RegistrationUseCase

class App : Application() {
    private val loginApi: ILoginApi by lazy { RoomLoginApi(getAccountDao()) }

    val loginDataSource: LoginUseCase by lazy {
        LoginDataSource(app.loginApi, Handler(Looper.getMainLooper()))
    }
    val registrationDataSource: RegistrationUseCase by lazy {
        RegistrationDataSource(app.loginApi, Handler(Looper.getMainLooper()))
    }
    val forgetPasswordDataSource: ForgetPasswordUseCase by lazy {
        ForgetPasswordDataSource(app.loginApi, Handler(Looper.getMainLooper()))
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