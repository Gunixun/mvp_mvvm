package com.example.mvp_mvvm.ui.forget_password

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mvp_mvvm.R
import com.example.mvp_mvvm.app
import com.example.mvp_mvvm.databinding.FragmentForgetPasswordBinding
import com.example.mvp_mvvm.domain.entities.Account
import com.example.mvp_mvvm.ui.AppState
import com.example.mvp_mvvm.ui.BaseFragment
import com.example.mvp_mvvm.utils.*

class ForgetPasswordFragment :
    BaseFragment<FragmentForgetPasswordBinding>(FragmentForgetPasswordBinding::inflate) {

    private var viewModel: ForgetPasswordContract.ViewModel? = null

    companion object {
        fun newInstance() = ForgetPasswordFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        viewModel = activity?.app?.let { ForgetPasswordViewModel(it.forgetPasswordDataSource) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel?.getLiveData()?.subscribe(Handler(Looper.getMainLooper())) { state ->
            renderData(state)
        }
        binding.restoreButton.setOnClickListener {
            viewModel?.findAccount(
                binding.emailTextView.text.toString()
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
                forgetPasswordData(result.account)
            }
            is AppState.Error -> {
                showError(result.error)
            }
        }
    }

    private fun showError(error: Exception) {
        val text = when (error) {
            is ForgetPasswordException -> {
                getString(R.string.error_forget_password)
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

    private fun forgetPasswordData(account: Account) {
        Toast.makeText(context, getString(R.string.success_registration), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.getLiveData()?.unsubscribeAll()
    }

}