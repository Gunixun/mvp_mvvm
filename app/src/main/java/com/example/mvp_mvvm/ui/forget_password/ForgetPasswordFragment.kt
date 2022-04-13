package com.example.mvp_mvvm.ui.forget_password

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.mvp_mvvm.R
import com.example.mvp_mvvm.app
import com.example.mvp_mvvm.databinding.FragmentForgetPasswordBinding
import com.example.mvp_mvvm.domain.entities.AccountEntity
import com.example.mvp_mvvm.ui.BaseFragment
import com.example.mvp_mvvm.utils.*

class ForgetPasswordFragment :
    BaseFragment<FragmentForgetPasswordBinding>(FragmentForgetPasswordBinding::inflate),
    ForgetPasswordContract.ForgetPasswordViewInterface {

    private var presenter: ForgetPasswordContract.ForgetPasswordPresenterInterface? = null


    companion object {
        fun newInstance() = ForgetPasswordFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = activity?.app?.let { ForgetPasswordPresenter(it.forgetPasswordUseCase) }
        presenter?.onAttachView(this)

        binding.restoreButton.setOnClickListener {
            presenter?.findAccount(
                binding.emailTextView.text.toString()
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
    }

    override fun forgetPasswordData(account: AccountEntity) {
        Toast.makeText(context, getString(R.string.success_registration), Toast.LENGTH_SHORT).show()
    }

}