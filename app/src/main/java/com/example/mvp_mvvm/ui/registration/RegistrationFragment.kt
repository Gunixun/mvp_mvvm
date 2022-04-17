package com.example.mvp_mvvm.ui.registration

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mvp_mvvm.R
import com.example.mvp_mvvm.app
import com.example.mvp_mvvm.databinding.FragmentRegistrationBinding
import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.ui.AppState
import com.example.mvp_mvvm.ui.BaseFragment
import com.example.mvp_mvvm.utils.*

class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate){

    private var viewModel: RegistrationContract.ViewModel? = null

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        viewModel = activity?.app?.let { RegistrationViewModel(it.registrationDataSource) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel?.getLiveData()?.subscribe(Handler(Looper.getMainLooper())) { state ->
            renderData(state)
        }

        binding.buttonCreate.setOnClickListener {
            viewModel?.onRegistration(
                binding.loginTextView.text.toString(),
                binding.passwordTextView.text.toString(),
                binding.emailTextView.text.toString(),
            )
        }
    }

    private fun renderData(result: AppState) {
        binding.progress.isVisible = false
        when (result) {
            is AppState.Loading -> {
                binding.progress.isVisible = true
            }
            is AppState.Success -> {
                loadAccountData(result.account)
            }
            is AppState.Error -> {
                showError(result.error)
            }
        }
    }

    private fun showError(error: Exception) {
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
        binding.root.setBackgroundColor(Color.RED)
    }

    private fun loadAccountData(account: Account) {
        Toast.makeText(context, getString(R.string.success_registration), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.getLiveData()?.unsubscribeAll()
    }
}