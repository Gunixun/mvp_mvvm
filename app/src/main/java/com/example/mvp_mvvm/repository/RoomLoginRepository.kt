package com.example.mvp_mvvm.repository

import android.os.Handler
import android.os.Looper
import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.domain.AccountEntity
import com.example.mvp_mvvm.domain.AccountsDAO
import com.example.mvp_mvvm.utils.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class RoomLoginRepository(private val localDataSource: AccountsDAO) : ILoginRepository {

    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    override fun getAllLocalAccount(): List<AccountEntity> = localDataSource.getAllAccountData()

    private fun checkData(login: String, password: String): java.lang.Exception?{
        if (login.isEmpty()){
            return LoginException()
        }

        if (password.isEmpty()){
            return PasswordException()
        }
        return null
    }

    override fun getAuthorization(
        login: String,
        password: String,
        callback: CallbackData<AccountData>
    ) {
        val res = checkData(login, password)
        if (res != null){
            callback.onError(res)
            return
        }

        executor.execute {
            try {
                val accountsList = getAllLocalAccount()
                for (account in accountsList) {
                    if (account.login == login && account.password == password) {
                        handler.post {
                            callback.onSuccess(convertAccountEntityToData(account))
                        }
                        return@execute
                    }
                }
                handler.post {
                    callback.onError(SingInException())
                }
            } catch (exc: Exception) {
                handler.post { callback.onError(exc) }
            }
        }
    }
}