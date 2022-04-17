package com.example.mvp_mvvm.ui.registration

import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.domain.use_cases.RegistrationUseCase
import com.example.mvp_mvvm.ui.utils.AppState
import com.example.mvp_mvvm.utils.CallbackData
import com.example.mvp_mvvm.utils.Publisher

class RegistrationViewModel(
    private val registrationDataSource: RegistrationUseCase
) : RegistrationContract.ViewModel {

    private val liveData: Publisher<AppState> = Publisher()

    override fun getLiveData(): Publisher<AppState> = liveData

    override fun onRegistration(login: String, password: String, email: String) {
        liveData.postValue(AppState.Loading)
        registrationDataSource.register(login, password, email, object : CallbackData<Account> {
            override fun onSuccess(result: Account) {
                liveData.postValue(AppState.Success(result))
            }
            override fun onError(error: Exception) {
                liveData.postValue(AppState.Error(error))
            }
        })
    }
}