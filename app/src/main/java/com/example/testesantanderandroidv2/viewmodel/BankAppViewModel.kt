package com.example.testesantanderandroidv2.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.example.testesantanderandroidv2.model.Statement
import com.example.testesantanderandroidv2.model.StatementResponse
import com.example.testesantanderandroidv2.model.User
import com.example.testesantanderandroidv2.model.UserResponse
import com.example.testesantanderandroidv2.repository.BankAppRepository

class BankAppViewModel(application: Application) : AndroidViewModel(application) {

    private val bankAppRepository = BankAppRepository.BankAppRepositoryProvider.providerBankAppRepository()
    private val statementResponse: MutableLiveData<StatementResponse> = MutableLiveData()
    private val userResponse: MutableLiveData<UserResponse> = MutableLiveData()
    private val getUser: MutableLiveData<User> = MutableLiveData()
    private val showError: MutableLiveData<String> = MutableLiveData()

    fun statementResponseObservable(): LiveData<StatementResponse> {
        return statementResponse
    }

    fun userResponseObservable(): LiveData<UserResponse> {
        return userResponse
    }

    fun getUserObservable(): LiveData<User> {
        return getUser
    }

    fun getStatement(idUser: String) {
        val statementResponseObservable: LiveData<StatementResponse> = bankAppRepository.getStatement(idUser)
        handlerError()
        statementResponseObservable.observeForever(object : Observer<StatementResponse> {
            override fun onChanged(t: StatementResponse?) {
                statementResponse.postValue(t)
                statementResponseObservable.removeObserver(this)
            }
        })
    }

    fun setUser() {
        val userResponseObservable: LiveData<UserResponse> = bankAppRepository.getUser()
        handlerError()
        userResponseObservable.observeForever(object : Observer<UserResponse> {
            override fun onChanged(t: UserResponse?) {
                userResponse.postValue(t)
                userResponseObservable.removeObserver(this)
            }
        })
    }

    private fun handlerError() {
        val error: LiveData<String> = bankAppRepository.getError()
        error.observeForever(object : Observer<String> {
            override fun onChanged(t: String?) {
                showError.postValue(t)
                error.removeObserver(this)
            }
        })
    }

    fun setUserParcelable(user: User) {
        getUser.postValue(user)
    }
}