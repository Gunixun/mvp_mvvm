package com.example.mvp_mvvm.ui.login

import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.domain.use_cases.LoginUseCase
import com.example.mvp_mvvm.ui.AppState
import com.example.mvp_mvvm.utils.CallbackData
import com.example.mvp_mvvm.utils.Publisher

class LoginViewModel(
    private val loginDataSource: LoginUseCase
) : LoginContract.ViewModel {

    private val liveData: Publisher<AppState> = Publisher()

    override fun getLiveData(): Publisher<AppState> = liveData

    override fun onLogin(login: String, password: String) {
        liveData.postValue(AppState.Loading)
        loginDataSource.login(login, password, object : CallbackData<Account> {
            override fun onSuccess(result: Account) {
                liveData.postValue(AppState.Success(result))
            }
            override fun onError(error: Exception) {
                liveData.postValue(AppState.Error(error))
            }
        })
    }
}