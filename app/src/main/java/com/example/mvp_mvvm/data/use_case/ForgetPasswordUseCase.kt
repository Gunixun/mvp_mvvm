package com.example.mvp_mvvm.data.use_case

import android.os.Handler
import androidx.annotation.MainThread
import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.usecase.IForgetPasswordUseCase
import com.example.mvp_mvvm.utils.CallbackData
import com.example.mvp_mvvm.utils.convertAccountEntityToData

class ForgetPasswordUseCase(
    private val api: ILoginApi,
    private val uiHandler: Handler
) : IForgetPasswordUseCase {

    override fun forgetPassword(
        email: String,
        @MainThread callback: CallbackData<AccountData>
    ) {
        Thread {
            try {
                val account = api.forgotPassword(email)
                uiHandler.post {
                    callback.onSuccess(convertAccountEntityToData(account))
                }
            } catch (exc: Exception) {
                uiHandler.post { callback.onError(exc) }
            }
        }.start()
    }
}