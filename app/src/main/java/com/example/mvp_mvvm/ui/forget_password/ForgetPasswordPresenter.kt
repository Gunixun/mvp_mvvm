package com.example.mvp_mvvm.ui.forget_password

import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.domain.usecase.IForgetPasswordUseCase
import com.example.mvp_mvvm.utils.CallbackData

class ForgetPasswordPresenter(
    private val forgetPasswordUseCase: IForgetPasswordUseCase
) : ForgetPasswordContract.ForgetPasswordPresenterInterface {

    private var view: ForgetPasswordContract.ForgetPasswordViewInterface? = null

    override fun onAttachView(view: ForgetPasswordContract.ForgetPasswordViewInterface) {
        this.view = view
    }

    override fun findAccount(email: String) {
        view?.showProgress()
        forgetPasswordUseCase.forgetPassword(email, object : CallbackData<AccountData> {
            override fun onSuccess(result: AccountData) {
                view?.hideProgress()
                view?.forgetPasswordData(result)
            }

            override fun onError(error: Exception) {
                view?.hideProgress()
                view?.showError(error)
            }
        })
    }
}