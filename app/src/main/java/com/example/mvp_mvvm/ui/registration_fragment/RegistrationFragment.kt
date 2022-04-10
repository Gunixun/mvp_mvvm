package com.example.mvp_mvvm.ui.registration_fragment

import com.example.mvp_mvvm.databinding.RegistrationFragmentBinding
import com.example.mvp_mvvm.ui.BaseFragment

class RegistrationFragment :
    BaseFragment<RegistrationFragmentBinding>(RegistrationFragmentBinding::inflate) {

    companion object {
        fun newInstance() = RegistrationFragment()
    }
}