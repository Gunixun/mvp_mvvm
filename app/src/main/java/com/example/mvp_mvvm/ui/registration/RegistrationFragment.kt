package com.example.mvp_mvvm.ui.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mvp_mvvm.R
import com.example.mvp_mvvm.databinding.RegistrationFragmentBinding
import com.example.mvp_mvvm.model.AccountData
import com.example.mvp_mvvm.contract.RegistrationContract
import com.example.mvp_mvvm.presenter.RegistrationPresenter
import com.example.mvp_mvvm.ui.BaseFragment
import java.lang.Exception

class RegistrationFragment :
    BaseFragment<RegistrationFragmentBinding>(RegistrationFragmentBinding::inflate),
    RegistrationContract.RegistrationViewInterface {

    private val presenter: RegistrationContract.RegistrationPresenterInterface = RegistrationPresenter()

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onAttachView(this)

        binding.buttonCreate.setOnClickListener {
            presenter.onRegistration(
                binding.textViewLogin.text.toString(),
                binding.textViewPassword.text.toString(),
                binding.textViewEmail.text.toString(),
            )
        }
    }

    override fun showProgress() {
        binding.progress.isVisible= true
    }

    override fun hideProgress() {
        binding.progress.isVisible = false
    }

    override fun showRegistrationException() {
        Toast.makeText(context, getString(R.string.error_registration), Toast.LENGTH_SHORT).show()
    }

    override fun showLoginError() {
        Toast.makeText(context, getString(R.string.error_login_empty), Toast.LENGTH_SHORT).show()
    }

    override fun showPasswordException() {
        Toast.makeText(context, getString(R.string.error_password_empty), Toast.LENGTH_SHORT).show()
    }

    override fun showEmailError() {
        Toast.makeText(context, getString(R.string.error_email_empty), Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: Exception) {
        Toast.makeText(
            context,
            getString(R.string.unexpected_error_occurred) + error.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun loadAccountData(account: AccountData) {
        Toast.makeText(context, getString(R.string.success_registration), Toast.LENGTH_SHORT).show()
    }
}