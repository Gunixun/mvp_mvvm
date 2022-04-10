package com.example.mvp_mvvm.utils

interface CallbackData<T> {

    fun onSuccess(result: T)

    fun onError(error: Exception)

}