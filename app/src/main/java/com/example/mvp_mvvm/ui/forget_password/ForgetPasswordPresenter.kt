package com.example.mvp_mvvm.ui.forget_password

import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.domain.usecase.IForgetPasswordUseCase
import com.example.mvp_mvvm.utils.CallbackData

class ForgetPasswordPresenter(
    private val forgetPasswordUseCase: IForgetPasswordUseCase
) : ForgetPasswordContract.ForgetPasswordPresenterInterface {

    private var isSuccess: Boolean = false
    private var view: ForgetPasswordContract.ForgetPasswordViewInterface? = null

    override fun onAttachView(view: ForgetPasswordContract.ForgetPasswordViewInterface) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        }
    }

    override fun findAccount(email: String) {
        view?.showProgress()
        forgetPasswordUseCase.forgetPassword(email, object : CallbackData<Account> {
            override fun onSuccess(result: Account) {
                view?.hideProgress()
                view?.forgetPasswordData(result)
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