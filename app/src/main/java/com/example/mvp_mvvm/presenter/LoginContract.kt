package com.example.mvp_mvvm.presenter

class LoginContract {
    interface LoginViewInterface {
        fun showProgress()
        fun hideProgress()
        fun setError(error: String)
    }

    interface LoginPresenterInterface  {
        fun onAttachView(view: LoginViewInterface)
        fun onAuthorization(login: String, password: String): Boolean
    }
}