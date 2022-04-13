package com.example.mvp_mvvm.data

import com.example.mvp_mvvm.domain.AccountEntity
import com.example.mvp_mvvm.domain.AccountsDAO
import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.utils.*
import java.util.*

class RoomLoginApi(private val localDataSource: AccountsDAO) : ILoginApi {

    override fun getAllAccounts(): List<AccountEntity> = localDataSource.getAllAccountData()

    private fun checkData(login: String?, password: String?, email: String?){
        if (login != null && login.isEmpty()){
            throw LoginException()
        }

        if (password != null && password.isEmpty()){
            throw PasswordException()
        }

        if (email != null && email.isEmpty()){
            throw EmailException()
        }
    }

    override fun login(login: String, password: String) : AccountEntity {
        checkData(login, password, null)
        val accountsList = getAllAccounts()
        for (account in accountsList) {
            if (account.login == login && account.password == password) {
                return account
            }
        }
        throw SingInException()
    }

    override fun register(login: String, password: String, email: String) : AccountEntity {
        checkData(login, password, null)
        val accountsList = getAllAccounts()
        for (account in accountsList) {
            if(account.login == login && account.email == email) {
                throw RegistrationException()
            }
        }
        val newAccount = AccountEntity(uid=null, login = login, password = password, email = email)
        localDataSource.registration(newAccount)
        return newAccount
    }

    override fun forgotPassword(email: String) : AccountEntity {
        checkData(null, null, email)
        val accountsList = getAllAccounts()
        for (account in accountsList) {
            if (account.email == email) {
                return account
            }
        }
        throw ForgetPasswordException()
    }
}