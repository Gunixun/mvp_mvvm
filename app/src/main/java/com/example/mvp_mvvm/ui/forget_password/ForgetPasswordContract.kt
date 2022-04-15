package com.example.mvp_mvvm.ui.forget_password

import com.example.mvp_mvvm.domain.entities.AccountEntity

class ForgetPasswordContract {
    interface ForgetPasswordViewInterface {
        fun showProgress()
        fun hideProgress()
        fun setSuccess()
        fun showError(error: Exception)
        fun forgetPasswordData(account: AccountEntity)
    }

    interface ForgetPasswordPresenterInterface  {
        fun onAttachView(view: ForgetPasswordViewInterface)
        fun findAccount(email: String)
        fun onDetach()
    }
}