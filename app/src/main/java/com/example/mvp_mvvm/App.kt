package com.example.mvp_mvvm

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.room.Room
import com.example.mvp_mvvm.data.RoomLoginApi
import com.example.mvp_mvvm.data.use_case.ForgetPasswordUseCase
import com.example.mvp_mvvm.data.use_case.LoginUseCase
import com.example.mvp_mvvm.data.use_case.RegistrationUseCase
import com.example.mvp_mvvm.data.db.AccountsDao
import com.example.mvp_mvvm.data.db.AccountsDb
import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.usecase.IForgetPasswordUseCase
import com.example.mvp_mvvm.domain.usecase.ILoginUseCase
import com.example.mvp_mvvm.domain.usecase.IRegistrationUseCase

class App : Application() {
    private val loginApi: ILoginApi by lazy { RoomLoginApi(getAccountDao()) }

    val loginUseCase: ILoginUseCase by lazy {
        LoginUseCase(app.loginApi, Handler(Looper.getMainLooper()))
    }
    val registrationUseCase: IRegistrationUseCase by lazy {
        RegistrationUseCase(app.loginApi, Handler(Looper.getMainLooper()))
    }
    val forgetPasswordUseCase: IForgetPasswordUseCase by lazy {
        ForgetPasswordUseCase(app.loginApi, Handler(Looper.getMainLooper()))
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