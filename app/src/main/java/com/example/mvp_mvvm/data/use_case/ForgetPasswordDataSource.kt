package com.example.mvp_mvvm.data.use_case

import android.os.Handler
import androidx.annotation.MainThread
import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.usecase.ForgetPasswordUseCase
import com.example.mvp_mvvm.utils.CallbackData

class ForgetPasswordDataSource(
    private val api: ILoginApi,
    private val uiHandler: Handler
) : ForgetPasswordUseCase {

    override fun forgetPassword(
        email: String,
        @MainThread callback: CallbackData<Account>
    ) {
        Thread {
            try {
                val account = api.forgotPassword(email)
                uiHandler.post {
                    callback.onSuccess(account)
                }
            } catch (exc: Exception) {
                uiHandler.post { callback.onError(exc) }
            }
        }.start()
    }
}