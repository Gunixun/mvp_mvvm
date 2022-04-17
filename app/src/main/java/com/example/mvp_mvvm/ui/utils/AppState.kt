package com.example.mvp_mvvm.ui.utils

import com.example.mvp_mvvm.domain.entities.Account


sealed class AppState {
    data class Success(val account: Account) : AppState()
    data class Error(val error: Exception) : AppState()
    object Loading : AppState()
}