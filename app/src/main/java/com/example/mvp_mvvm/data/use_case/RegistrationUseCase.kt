package com.example.mvp_mvvm.data.use_case

import android.os.Handler
import androidx.annotation.MainThread
import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.usecase.IRegistrationUseCase
import com.example.mvp_mvvm.utils.CallbackData

class RegistrationUseCase(
    private val api: ILoginApi,
    private val uiHandler: Handler
) : IRegistrationUseCase {
    override fun register(
        login: String,
        password: String,
        email: String,
        @MainThread callback: CallbackData<Account>
    ) {
        Thread {
            try {
                val account = api.register(login, password, email)
                uiHandler.post {
                    callback.onSuccess(account)
                }
            } catch (exc: Exception) {
                uiHandler.post { callback.onError(exc) }
            }
        }.start()
    }
}