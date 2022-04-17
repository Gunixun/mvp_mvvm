package com.example.mvp_mvvm.ui.registration

import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.domain.usecase.RegistrationUseCase
import com.example.mvp_mvvm.utils.CallbackData

class RegistrationPresenter(
    private val registrationUseCase: RegistrationUseCase
) : RegistrationContract.RegistrationPresenterInterface {

    private var isSuccess: Boolean = false
    private var view: RegistrationContract.RegistrationViewInterface? = null

    override fun onAttachView(view: RegistrationContract.RegistrationViewInterface) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        }
    }

    override fun onRegistration(login: String, password: String, email: String) {
        view?.showProgress()
        registrationUseCase.register(login, password, email, object : CallbackData<Account> {
            override fun onSuccess(result: Account) {
                view?.hideProgress()
                view?.loadAccountData(result)
                view?.setSuccess()
                isSuccess = true
            }
            override fun onError(error: Exception) {
                view?.hideProgress()
                view?.showError(error)
                isSuccess = false
            }
        })
    }

    override fun onDetach() {
        this.view = null
    }
}