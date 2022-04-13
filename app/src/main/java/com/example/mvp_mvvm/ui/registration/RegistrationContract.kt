package com.example.mvp_mvvm.ui.registration

import com.example.mvp_mvvm.domain.entities.AccountEntity

class RegistrationContract {
    interface RegistrationViewInterface {
        fun showProgress()
        fun hideProgress()
        fun showError(error: Exception)
        fun loadAccountData(account: AccountEntity)
    }

    interface RegistrationPresenterInterface  {
        fun onAttachView(view: RegistrationViewInterface)
        fun onRegistration(login: String, password: String, email: String)
    }
}