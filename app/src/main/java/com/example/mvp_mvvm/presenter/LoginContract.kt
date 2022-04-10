package com.example.mvp_mvvm.presenter

import com.example.mvp_mvvm.model.AccountData
import java.lang.Exception

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