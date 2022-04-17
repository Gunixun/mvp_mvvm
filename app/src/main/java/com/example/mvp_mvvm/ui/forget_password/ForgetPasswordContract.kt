package com.example.mvp_mvvm.ui.forget_password

import com.example.mvp_mvvm.ui.utils.AppState
import com.example.mvp_mvvm.utils.Publisher

class ForgetPasswordContract {
    interface ViewModel {
        fun getLiveData() : Publisher<AppState>
        fun findAccount(email: String)
    }
}