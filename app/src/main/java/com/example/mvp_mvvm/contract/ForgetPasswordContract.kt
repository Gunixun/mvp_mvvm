package com.example.mvp_mvvm.contract

import com.example.mvp_mvvm.model.AccountData
import java.lang.Exception

class ForgetPasswordContract {
    interface ForgetPasswordViewInterface {
        fun showProgress()
        fun hideProgress()
        fun showEmailError()
        fun showForgetPasswordException()
        fun showError(error: Exception)
        fun forgetPasswordData(account: AccountData)
    }

    interface ForgetPasswordPresenterInterface  {
        fun onAttachView(view: ForgetPasswordViewInterface)
        fun findAccount(email: String)
    }
}