package com.example.mvp_mvvm.ui.registration

import com.example.mvp_mvvm.ui.utils.AppState
import com.example.mvp_mvvm.utils.Publisher

class RegistrationContract {

    interface ViewModel  {
        fun getLiveData() : Publisher<AppState>
        fun onRegistration(login: String, password: String, email: String)
    }
}