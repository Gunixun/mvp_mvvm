package com.example.mvp_mvvm.presenter

import com.example.mvp_mvvm.contract.RegistrationContract
import com.example.mvp_mvvm.model.AccountData
import com.example.mvp_mvvm.repository.IRegistrationRepository
import com.example.mvp_mvvm.repository.RoomRegistrationRepository
import com.example.mvp_mvvm.ui.App
import com.example.mvp_mvvm.utils.*

class RegistrationPresenter : RegistrationContract.RegistrationPresenterInterface {

    private var view: RegistrationContract.RegistrationViewInterface? = null
    private val repository: IRegistrationRepository =
        RoomRegistrationRepository(App.getAccountDao())


    override fun onAttachView(view: RegistrationContract.RegistrationViewInterface) {
        this.view = view
    }

    override fun onRegistration(login: String, password: String, email: String) {
        view?.showProgress()
        repository.registration(login, password, email, object : CallbackData<AccountData> {
            override fun onSuccess(result: AccountData) {
                view?.hideProgress()
                view?.loadAccountData(result)
            }

            override fun onError(error: Exception) {
                view?.hideProgress()
                if (error is RegistrationException) {
                    view?.showRegistrationException()
                } else if (error is PasswordException) {
                    view?.showPasswordException()
                } else if (error is LoginException) {
                    view?.showLoginError()
                } else if (error is EmailException) {
                    view?.showEmailError()
                } else {
                    view?.showError(error)
                }
            }

        })
    }
}