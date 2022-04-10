package com.example.mvp_mvvm.ui.authorization

import android.os.Bundle
import android.view.View
import com.example.mvp_mvvm.databinding.AuthorizationFragmentBinding
import com.example.mvp_mvvm.ui.BaseFragment
import com.example.mvp_mvvm.ui.NavigationActivity
import com.example.mvp_mvvm.ui.registration_fragment.RegistrationFragment

class AuthorizationFragment :
    BaseFragment<AuthorizationFragmentBinding>(AuthorizationFragmentBinding::inflate) {

    companion object {
        fun newInstance() = AuthorizationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        connectSignals()
    }

    private fun connectSignals(){
        binding.buttonRegistration.setOnClickListener{
            activity?.let {
                if (it is NavigationActivity){
                    it.navigationTo(RegistrationFragment.newInstance(), true)
                }
            }
        }
    }
}