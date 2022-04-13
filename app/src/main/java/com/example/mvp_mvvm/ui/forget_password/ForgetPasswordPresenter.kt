package com.example.mvp_mvvm.ui.forget_password

import com.example.mvp_mvvm.App
import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.repository.IForgetPasswordRepository
import com.example.mvp_mvvm.repository.RoomForgetPasswordRepository
import com.example.mvp_mvvm.utils.CallbackData
import com.example.mvp_mvvm.utils.EmailException
import com.example.mvp_mvvm.utils.ForgetPasswordException

class ForgetPasswordPresenter : ForgetPasswordContract.ForgetPasswordPresenterInterface {

    private var view: ForgetPasswordContract.ForgetPasswordViewInterface? = null
    private val repository: IForgetPasswordRepository =
        RoomForgetPasswordRepository(App.getAccountDao())

    override fun onAttachView(view: ForgetPasswordContract.ForgetPasswordViewInterface) {
        this.view = view
    }

    override fun findAccount(email: String) {
        view?.showProgress()
        repository.findAccount(email, object : CallbackData<AccountData> {
            override fun onSuccess(result: AccountData) {
                view?.hideProgress()
                view?.forgetPasswordData(result)
            }

            override fun onError(error: Exception) {
                view?.hideProgress()
                if (error is ForgetPasswordException){
                    view?.showForgetPasswordException()
                } else if (error is EmailException) {
                    view?.showEmailError()
                } else {
                    view?.showError(error)
                }
            }

        })
    }
}