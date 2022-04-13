package com.example.mvp_mvvm.ui.forget_password

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mvp_mvvm.R
import com.example.mvp_mvvm.databinding.ForgetPasswordPasswordBinding
import com.example.mvp_mvvm.domain.AccountData
import com.example.mvp_mvvm.ui.BaseFragment

class ForgetPasswordFragment :
    BaseFragment<ForgetPasswordPasswordBinding>(ForgetPasswordPasswordBinding::inflate),
    ForgetPasswordContract.ForgetPasswordViewInterface {

    private val presenter: ForgetPasswordContract.ForgetPasswordPresenterInterface =
        ForgetPasswordPresenter()

    companion object {
        fun newInstance() = ForgetPasswordFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onAttachView(this)

        binding.buttonRestore.setOnClickListener {
            presenter.findAccount(
                binding.textViewEmail.text.toString()
            )
        }
    }

    override fun showProgress() {
        binding.progress.isVisible= true
    }

    override fun hideProgress() {
        binding.progress.isVisible = false
    }

    override fun showEmailError() {
        Toast.makeText(context, getString(R.string.error_email_empty), Toast.LENGTH_SHORT).show()
    }

    override fun showForgetPasswordException() {
        Toast.makeText(context, getString(R.string.error_forget_password), Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: Exception) {
        Toast.makeText(
            context,
            getString(R.string.unexpected_error_occurred) + error.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun forgetPasswordData(account: AccountData) {
        Toast.makeText(context, getString(R.string.success_registration), Toast.LENGTH_SHORT).show()
    }

}