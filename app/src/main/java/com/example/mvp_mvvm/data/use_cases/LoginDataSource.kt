package com.example.mvp_mvvm.data.use_cases

import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.domain.use_cases.LoginUseCase
import com.example.mvp_mvvm.utils.CallbackData

class LoginDataSource(
    private val api: ILoginApi,
) : LoginUseCase {
    override fun login(
        login: String,
        password: String,
        callback: CallbackData<Account>
    ) {
        Thread {
            try {
                val account = api.login(login, password)
                callback.onSuccess(account)
            } catch (exc: Exception) {
                callback.onError(exc)
            }
        }.start()
    }
}