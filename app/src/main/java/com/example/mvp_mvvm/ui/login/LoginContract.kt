package com.example.mvp_mvvm.ui.login

import com.example.mvp_mvvm.domain.entities.AccountEntity

class LoginContract {
    interface LoginViewInterface {
        fun showProgress()
        fun hideProgress()
        fun setSuccess()
        fun showError(error: Exception)
        fun loadAccountData(account: AccountEntity)
    }

    interface LoginPresenterInterface  {
        fun onAttachView(view: LoginViewInterface)
        fun onLogin(login: String, password: String)
        fun onDetach()
    }
}