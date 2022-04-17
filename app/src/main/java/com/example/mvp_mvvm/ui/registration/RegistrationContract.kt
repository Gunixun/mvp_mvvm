package com.example.mvp_mvvm.ui.registration

import com.example.mvp_mvvm.domain.entities.Account

class RegistrationContract {
    interface RegistrationViewInterface {
        fun showProgress()
        fun hideProgress()
        fun setSuccess()
        fun showError(error: Exception)
        fun loadAccountData(account: Account)
    }

    interface RegistrationPresenterInterface  {
        fun onAttachView(view: RegistrationViewInterface)
        fun onRegistration(login: String, password: String, email: String)
        fun onDetach()
    }
}