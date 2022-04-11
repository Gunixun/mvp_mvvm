package com.example.mvp_mvvm.ui.authorization

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mvp_mvvm.R
import com.example.mvp_mvvm.databinding.AuthorizationFragmentBinding
import com.example.mvp_mvvm.model.AccountData
import com.example.mvp_mvvm.contract.LoginContract
import com.example.mvp_mvvm.presenter.LoginPresenter
import com.example.mvp_mvvm.ui.BaseFragment
import com.example.mvp_mvvm.ui.NavigationActivity
import com.example.mvp_mvvm.ui.forget_password.ForgetPasswordFragment
import com.example.mvp_mvvm.ui.registration.RegistrationFragment
import java.lang.Exception

class AuthorizationFragment :
    BaseFragment<AuthorizationFragmentBinding>(AuthorizationFragmentBinding::inflate),
    LoginContract.LoginViewInterface {

    private val presenter: LoginContract.LoginPresenterInterface = LoginPresenter()

    companion object {
        fun newInstance() = AuthorizationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onAttachView(this)

        connectSignals()
    }

    private fun connectSignals() {

        binding.buttonForgotPassword.setOnClickListener {
            activity?.let {
                if (it is NavigationActivity) {
                    it.navigationTo(ForgetPasswordFragment.newInstance(), true)
                }
            }
        }

        binding.buttonRegistration.setOnClickListener {
            activity?.let {
                if (it is NavigationActivity) {
                    it.navigationTo(RegistrationFragment.newInstance(), true)
                }
            }
        }

        binding.buttonSigIn.setOnClickListener {
            presenter.onAuthorization(
                binding.textViewLogin.text.toString(),
                binding.textViewPassword.text.toString()
            )
        }
    }

    override fun showProgress() {
        binding.progress.isVisible= true
    }

    override fun hideProgress() {
        binding.progress.isVisible = false
    }

    override fun showLoginError() {
        Toast.makeText(context, getString(R.string.error_login_empty), Toast.LENGTH_SHORT).show()
    }

    override fun showPasswordException() {
        Toast.makeText(context, getString(R.string.error_password_empty), Toast.LENGTH_SHORT).show()
    }

    override fun showSigInException() {
        Toast.makeText(context, getString(R.string.error_sig_in), Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: Exception) {
        Toast.makeText(
            context,
            getString(R.string.unexpected_error_occurred) + error.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun loadAccountData(account: AccountData) {
        Toast.makeText(context, getString(R.string.success_sig_in), Toast.LENGTH_SHORT).show()
    }
}