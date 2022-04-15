package com.example.mvp_mvvm.data

import com.example.mvp_mvvm.data.db.AccountDTO
import com.example.mvp_mvvm.data.db.AccountsDAO
import com.example.mvp_mvvm.domain.ILoginApi
import com.example.mvp_mvvm.domain.entities.AccountEntity
import com.example.mvp_mvvm.utils.*

class RoomLoginApi(private val localDataSource: AccountsDAO) : ILoginApi {

    fun getAllAccounts(): List<AccountDTO> = localDataSource.getAllAccountData()

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
                return convertAccountDtoToEntity(account)
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
        val newAccount = AccountDTO(uid=null, login = login, password = password, email = email)
        localDataSource.registration(newAccount)
        return convertAccountDtoToEntity(newAccount)
    }

    override fun forgotPassword(email: String) : AccountEntity {
        checkData(null, null, email)
        val accountsList = getAllAccounts()
        for (account in accountsList) {
            if (account.email == email) {
                return convertAccountDtoToEntity(account)
            }
        }
        throw ForgetPasswordException()
    }
}