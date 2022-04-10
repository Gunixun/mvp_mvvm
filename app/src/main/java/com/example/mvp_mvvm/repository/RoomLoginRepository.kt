package com.example.mvp_mvvm.repository

import android.os.Handler
import android.os.Looper
import com.example.mvp_mvvm.model.AccountData
import com.example.mvp_mvvm.model.AccountEntity
import com.example.mvp_mvvm.model.AccountsDAO
import com.example.mvp_mvvm.utils.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class RoomLoginRepository(private val localDataSource: AccountsDAO) : ILoginRepository {

    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    override fun getAllLocalAccount(): List<AccountEntity> = localDataSource.getAllAccountData()

    override fun getAuthorization(
        login: String,
        password: String,
        callback: CallbackData<AccountData>
    ) {
        if (login.isEmpty()){
            callback.onError(LoginException())
            return
        }

        if (password.isEmpty()){
            callback.onError(PasswordException())
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
                callback.onError(exc)
                handler.post { callback.onError(exc) }
            }
        }
    }
}