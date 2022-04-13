package com.example.mvp_mvvm.data.use_case

import android.os.Handler
import androidx.annotation.MainThread
import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.usecase.ILoginUseCase
import com.example.mvp_mvvm.utils.CallbackData
import com.example.mvp_mvvm.utils.convertAccountEntityToData

class LoginUseCase(
    private val api: ILoginApi,
    private val uiHandler: Handler
) : ILoginUseCase {
    override fun login(
        login: String,
        password: String,
        @MainThread callback: CallbackData<AccountData>
    ) {
        Thread {
            try {
                val account = api.login(login, password)
                uiHandler.post {
                    callback.onSuccess(convertAccountEntityToData(account))
                }
            } catch (exc: Exception) {
                uiHandler.post { callback.onError(exc) }
            }
        }.start()
    }
}