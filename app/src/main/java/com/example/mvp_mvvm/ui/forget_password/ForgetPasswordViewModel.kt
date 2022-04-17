package com.example.mvp_mvvm.ui.forget_password

import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.domain.use_cases.ForgetPasswordUseCase
import com.example.mvp_mvvm.ui.utils.AppState
import com.example.mvp_mvvm.utils.CallbackData
import com.example.mvp_mvvm.utils.Publisher

class ForgetPasswordViewModel(
    private val forgetPasswordDataSource: ForgetPasswordUseCase
) : ForgetPasswordContract.ViewModel {
    private val liveData: Publisher<AppState> = Publisher()

    override fun getLiveData(): Publisher<AppState> = liveData

    override fun findAccount(email: String) {
        liveData.postValue(AppState.Loading)
        forgetPasswordDataSource.forgetPassword(email, object : CallbackData<Account> {
            override fun onSuccess(result: Account) {
                liveData.postValue(AppState.Success(result))
            }
            override fun onError(error: Exception) {
                liveData.postValue(AppState.Error(error))
            }
        })
    }
}