package com.example.mvp_mvvm.ui.login

import com.example.mvp_mvvm.domain.entities.AccountEntity
import com.example.mvp_mvvm.domain.usecase.ILoginUseCase
import com.example.mvp_mvvm.utils.CallbackData

class LoginPresenter(
    private val loginUseCase: ILoginUseCase
) : LoginContract.LoginPresenterInterface {

    private var isSuccess: Boolean = false
    private var view: LoginContract.LoginViewInterface? = null

    override fun onAttachView(view: LoginContract.LoginViewInterface) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        loginUseCase.login(login, password, object : CallbackData<AccountEntity> {
            override fun onSuccess(result: AccountEntity) {
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