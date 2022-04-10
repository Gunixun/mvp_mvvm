package com.example.mvp_mvvm.ui.authorization

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.mvp_mvvm.databinding.AuthorizationFragmentBinding
import com.example.mvp_mvvm.presenter.LoginContract
import com.example.mvp_mvvm.ui.BaseFragment
import com.example.mvp_mvvm.ui.NavigationActivity
import com.example.mvp_mvvm.ui.registration_fragment.RegistrationFragment
import com.example.mvp_mvvm.utils.createMsgSnackBar

class AuthorizationFragment :
    BaseFragment<AuthorizationFragmentBinding>(AuthorizationFragmentBinding::inflate),
    LoginContract.LoginViewInterface {

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

    override fun showProgress() {
        binding.progress.isVisible= true
    }

    override fun hideProgress() {
        binding.progress.isVisible= false
    }

    override fun setError(error: String) {
        binding.layoutLogin.isVisible = true
        binding.root.createMsgSnackBar(
            text = error
        ).show()
    }
}