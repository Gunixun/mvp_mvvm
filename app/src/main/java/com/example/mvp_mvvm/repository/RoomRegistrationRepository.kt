package com.example.mvp_mvvm.repository

import android.os.Handler
import android.os.Looper
import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.domain.AccountEntity
import com.example.mvp_mvvm.domain.AccountsDAO
import com.example.mvp_mvvm.utils.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class RoomRegistrationRepository(private val localDataSource: AccountsDAO) :
    IRegistrationRepository {

    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    override fun getAllLocalAccount(): List<AccountEntity> = localDataSource.getAllAccountData()

    private fun checkData(login: String, password: String, email: String): Exception?{
        if (login.isEmpty()){
            return LoginException()
        }

        if (password.isEmpty()){
            return PasswordException()
        }

        if (email.isEmpty()){
            return EmailException()
        }
        return null
    }


    override fun registration(
        login: String,
        password: String,
        email: String,
        callback: CallbackData<AccountData>
    ) {
        val res = checkData(login, password, email)
        if (res != null){
            callback.onError(res)
            return
        }
        executor.execute {
            try {
                val accountsList = getAllLocalAccount()
                for (account in accountsList) {
                    if (account.login == login && account.email == email) {
                        handler.post { callback.onError(RegistrationException()) }
                        return@execute
                    }
                }
                val newAccount =
                    AccountEntity(uid=null, login = login, password = password, email = email)
                localDataSource.registration(newAccount)
                handler.post {
                    callback.onSuccess(convertAccountEntityToData(newAccount))
                }
            } catch (exc: Exception) {
                handler.post { callback.onError(exc) }
            }
        }
    }


}