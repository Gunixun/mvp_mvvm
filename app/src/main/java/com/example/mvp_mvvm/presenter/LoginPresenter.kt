package com.example.mvp_mvvm.presenter

import com.example.mvp_mvvm.model.AccountData
import com.example.mvp_mvvm.repository.ILoginRepository
import com.example.mvp_mvvm.repository.RoomLoginRepository
import com.example.mvp_mvvm.ui.App
import com.example.mvp_mvvm.utils.CallbackData
import com.example.mvp_mvvm.utils.LoginException
import com.example.mvp_mvvm.utils.PasswordException
import com.example.mvp_mvvm.utils.SingInException

class LoginPresenter: LoginContract.LoginPresenterInterface {

    private var view: LoginContract.LoginViewInterface? = null
    private val repository: ILoginRepository = RoomLoginRepository(App.getAccountDao())

    override fun onAttachView(view: LoginContract.LoginViewInterface) {
        this.view = view
    }

    override fun onAuthorization(login: String, password: String) {
        view?.showProgress()
        repository.getAuthorization(login, password, object : CallbackData<AccountData> {
            override fun onSuccess(result: AccountData) {
                view?.hideProgress()
                view?.loadAccountData(result)
            }

            override fun onError(error: Exception) {
                view?.hideProgress()
                if (error is SingInException){
                    view?.showSigInException()
                } else if (error is PasswordException){
                    view?.showPasswordException()
                } else if (error is LoginException){
                    view?.showLoginError()
                } else {
                    view?.showError(error)
                }
            }

        })
    }
}