package com.example.mvp_mvvm.ui.authorization

import com.example.mvp_mvvm.domain.AccountData

class LoginContract {
    interface LoginViewInterface {
        fun showProgress()
        fun hideProgress()
        fun showLoginError()
        fun showPasswordException()
        fun showSigInException()
        fun showError(error: Exception)
        fun loadAccountData(account: AccountData)
    }

    interface LoginPresenterInterface  {
        fun onAttachView(view: LoginViewInterface)
        fun onAuthorization(login: String, password: String)
    }
}