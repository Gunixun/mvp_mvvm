package com.example.mvp_mvvm.ui.login

import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.domain.usecase.ILoginUseCase
import com.example.mvp_mvvm.utils.CallbackData

class LoginPresenter(
    private val loginUseCase: ILoginUseCase
) : LoginContract.LoginPresenterInterface {

    private var view: LoginContract.LoginViewInterface? = null

    override fun onAttachView(view: LoginContract.LoginViewInterface) {
        this.view = view
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        loginUseCase.login(login, password, object : CallbackData<AccountData> {
            override fun onSuccess(result: AccountData) {
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