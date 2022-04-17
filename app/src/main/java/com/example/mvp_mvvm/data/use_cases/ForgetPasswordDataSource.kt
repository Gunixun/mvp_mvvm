package com.example.mvp_mvvm.data.use_cases

import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.domain.use_cases.ForgetPasswordUseCase
import com.example.mvp_mvvm.utils.CallbackData

class ForgetPasswordDataSource(
    private val api: ILoginApi
) : ForgetPasswordUseCase {

    override fun forgetPassword(
        email: String,
        callback: CallbackData<Account>
    ) {
        Thread {
            try {
                val account = api.forgotPassword(email)
                callback.onSuccess(account)
            } catch (exc: Exception) {
                callback.onError(exc)
            }
        }.start()
    }
}