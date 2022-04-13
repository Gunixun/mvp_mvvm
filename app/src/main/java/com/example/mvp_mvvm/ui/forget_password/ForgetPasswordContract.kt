package com.example.mvp_mvvm.ui.forget_password

import com.example.mvp_mvvm.domain.AccountData

class ForgetPasswordContract {
    interface ForgetPasswordViewInterface {
        fun showProgress()
        fun hideProgress()
        fun showError(error: Exception)
        fun forgetPasswordData(account: AccountData)
    }

    interface ForgetPasswordPresenterInterface  {
        fun onAttachView(view: ForgetPasswordViewInterface)
        fun findAccount(email: String)
    }
}