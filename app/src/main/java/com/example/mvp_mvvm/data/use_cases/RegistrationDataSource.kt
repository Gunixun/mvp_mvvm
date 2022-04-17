package com.example.mvp_mvvm.data.use_cases

import androidx.annotation.MainThread
import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.domain.use_cases.RegistrationUseCase
import com.example.mvp_mvvm.utils.CallbackData

class RegistrationDataSource(
    private val api: ILoginApi
) : RegistrationUseCase {
    override fun register(
        login: String,
        password: String,
        email: String,
        @MainThread callback: CallbackData<Account>
    ) {
        Thread {
            try {
                val account = api.register(login, password, email)
                callback.onSuccess(account)
            } catch (exc: Exception) {
                callback.onError(exc)
            }
        }.start()
    }
}