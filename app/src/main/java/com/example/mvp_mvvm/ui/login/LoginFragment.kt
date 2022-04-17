package com.example.mvp_mvvm.ui.login

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mvp_mvvm.R
import com.example.mvp_mvvm.app
import com.example.mvp_mvvm.databinding.FragmentLoginBinding
import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.ui.BaseFragment
import com.example.mvp_mvvm.ui.NavigationActivity
import com.example.mvp_mvvm.ui.forget_password.ForgetPasswordFragment
import com.example.mvp_mvvm.ui.registration.RegistrationFragment
import com.example.mvp_mvvm.utils.LoginException
import com.example.mvp_mvvm.utils.PasswordException
import com.example.mvp_mvvm.utils.SingInException

class LoginFragment :
    BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate),
    LoginContract.LoginViewInterface {

    private var presenter: LoginContract.LoginPresenterInterface? = null

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        presenter = activity?.app?.let { LoginPresenter(it.loginUseCase) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onAttachView(this)
        connectSignals()
    }

    private fun connectSignals() {

        binding.forgetPasswordButton.setOnClickListener {
            activity?.let {
                if (it is NavigationActivity) {
                    it.navigationTo(ForgetPasswordFragment.newInstance(), true)
                }
            }
        }

        binding.registrationButton.setOnClickListener {
            activity?.let {
                if (it is NavigationActivity) {
                    it.navigationTo(RegistrationFragment.newInstance(), true)
                }
            }
        }

        binding.sigInButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginTextView.text.toString(),
                binding.passwordTextView.text.toString()
            )
        }
    }

    override fun showProgress() {
        binding.progress.isVisible = true
    }

    override fun hideProgress() {
        binding.progress.isVisible = false
    }

    override fun setSuccess() {
        binding.root.setBackgroundColor(Color.GREEN)
    }

    override fun showError(error: Exception) {
        val text = when (error) {
            is SingInException -> {
                getString(R.string.error_sig_in)
            }
            is PasswordException -> {
                getString(R.string.error_password_empty)
            }
            is LoginException -> {
                getString(R.string.error_login_empty)
            }
            else -> {
                getString(R.string.unexpected_error_occurred) + error.toString()
            }
        }
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        binding.root.setBackgroundColor(Color.RED)
    }

    override fun loadAccountData(account: Account) {
        Toast.makeText(context, getString(R.string.success_sig_in), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDetach()
    }
}