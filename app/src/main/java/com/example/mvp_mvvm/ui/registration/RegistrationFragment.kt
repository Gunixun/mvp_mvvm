package com.example.mvp_mvvm.ui.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mvp_mvvm.R
import com.example.mvp_mvvm.app
import com.example.mvp_mvvm.databinding.FragmentRegistrationBinding
import com.example.mvp_mvvm.domain.entities.AccountEntity
import com.example.mvp_mvvm.ui.BaseFragment
import com.example.mvp_mvvm.utils.*

class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate),
    RegistrationContract.RegistrationViewInterface {

    private var presenter: RegistrationContract.RegistrationPresenterInterface? = null

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = activity?.app?.let { RegistrationPresenter(it.registrationUseCase) }
        presenter?.onAttachView(this)

        binding.buttonCreate.setOnClickListener {
            presenter?.onRegistration(
                binding.loginTextView.text.toString(),
                binding.passwordTextView.text.toString(),
                binding.emailTextView.text.toString(),
            )
        }
    }

    override fun showProgress() {
        binding.progress.isVisible = true
    }

    override fun hideProgress() {
        binding.progress.isVisible = false
    }

    override fun showError(error: Exception) {
        val text = when (error) {
            is RegistrationException -> {
                getString(R.string.error_registration)
            }
            is PasswordException -> {
                getString(R.string.error_password_empty)
            }
            is LoginException -> {
                getString(R.string.error_login_empty)
            }
            is EmailException -> {
                getString(R.string.error_email_empty)
            }
            else -> {
                getString(R.string.unexpected_error_occurred) + error.toString()
            }
        }
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun loadAccountData(account: AccountEntity) {
        Toast.makeText(context, getString(R.string.success_registration), Toast.LENGTH_SHORT).show()
    }
}