package com.example.mvp_mvvm.ui.authorization

import com.example.mvp_mvvm.databinding.AuthorizationFragmentBinding
import com.example.mvp_mvvm.ui.BaseFragment

class AuthorizationFragment :
    BaseFragment<AuthorizationFragmentBinding>(AuthorizationFragmentBinding::inflate) {

    companion object {
        fun newInstance() = AuthorizationFragment()
    }
}