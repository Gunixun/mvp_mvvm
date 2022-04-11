package com.example.mvp_mvvm.repository

import android.os.Handler
import android.os.Looper
import com.example.mvp_mvvm.model.AccountData
import com.example.mvp_mvvm.model.AccountEntity
import com.example.mvp_mvvm.model.AccountsDAO
import com.example.mvp_mvvm.utils.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class RoomForgetPasswordRepository(private val localDataSource: AccountsDAO) :
    IForgetPasswordRepository {

    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    override fun getAllLocalAccount(): List<AccountEntity> = localDataSource.getAllAccountData()

    override fun findAccount(email: String, callback: CallbackData<AccountData>) {

        if (email.isEmpty()){
            callback.onError(EmailException())
            return
        }

        executor.execute {
            try {
                val accountsList = getAllLocalAccount()
                for (account in accountsList) {
                    if (account.email == email) {
                        handler.post {
                            callback.onSuccess(convertAccountEntityToData(account))
                        }
                        return@execute
                    }
                }
                handler.post {
                    callback.onError(ForgetPasswordException())
                }
            } catch (exc: Exception) {
                handler.post { callback.onError(exc) }
            }
        }
    }
}