package com.example.mvp_mvvm.ui.registration

import com.example.mvp_mvvm.domain.AccountData

class RegistrationContract {
    interface RegistrationViewInterface {
        fun showProgress()
        fun hideProgress()
        fun showRegistrationException()
        fun showPasswordException()
        fun showLoginError()
        fun showEmailError()
        fun showError(error: Exception)
        fun loadAccountData(account: AccountData)
    }

    interface RegistrationPresenterInterface  {
        fun onAttachView(view: RegistrationViewInterface)
        fun onRegistration(login: String, password: String, email: String)
    }
}