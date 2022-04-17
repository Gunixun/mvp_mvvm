package com.example.mvp_mvvm.data.use_case

import android.os.Handler
import androidx.annotation.MainThread
import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.usecase.LoginUseCase
import com.example.mvp_mvvm.utils.CallbackData

class LoginDataSource(
    private val api: ILoginApi,
    private val uiHandler: Handler
) : LoginUseCase {
    override fun login(
        login: String,
        password: String,
        @MainThread callback: CallbackData<Account>
    ) {
        Thread {
            try {
                val account = api.login(login, password)
                uiHandler.post {
                    callback.onSuccess(account)
                }
            } catch (exc: Exception) {
                uiHandler.post { callback.onError(exc) }
            }
        }.start()
    }
}