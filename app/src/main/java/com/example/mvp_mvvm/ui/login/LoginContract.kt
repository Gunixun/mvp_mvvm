package com.example.mvp_mvvm.ui.login

import com.example.mvp_mvvm.domain.AccountData

class LoginContract {
    interface LoginViewInterface {
        fun showProgress()
        fun hideProgress()
        fun showError(error: Exception)
        fun loadAccountData(account: AccountData)
    }

    interface LoginPresenterInterface  {
        fun onAttachView(view: LoginViewInterface)
        fun onLogin(login: String, password: String)
    }
}