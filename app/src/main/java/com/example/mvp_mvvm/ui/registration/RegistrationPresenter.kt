package com.example.mvp_mvvm.ui.registration

import com.example.mvp_mvvm.domain.entities.AccountEntity
import com.example.mvp_mvvm.domain.usecase.IRegistrationUseCase
import com.example.mvp_mvvm.utils.CallbackData

class RegistrationPresenter(
    private val registrationUseCase: IRegistrationUseCase
) : RegistrationContract.RegistrationPresenterInterface {

    private var view: RegistrationContract.RegistrationViewInterface? = null

    override fun onAttachView(view: RegistrationContract.RegistrationViewInterface) {
        this.view = view
    }

    override fun onRegistration(login: String, password: String, email: String) {
        view?.showProgress()
        registrationUseCase.register(login, password, email, object : CallbackData<AccountEntity> {
            override fun onSuccess(result: AccountEntity) {
                view?.hideProgress()
                view?.loadAccountData(result)
            }
            override fun onError(error: Exception) {
                view?.hideProgress()
                view?.showError(error)
            }
        })
    }
}