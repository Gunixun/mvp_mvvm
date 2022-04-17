package com.example.mvp_mvvm.ui.login

import com.example.mvp_mvvm.ui.AppState
import com.example.mvp_mvvm.utils.Publisher

class LoginContract {

    interface ViewModel {
        fun getLiveData() : Publisher<AppState>
        fun onLogin(login: String, password: String)
    }
}