package com.example.testesantanderandroidv2.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.testesantanderandroidv2.data.CallApi
import com.example.testesantanderandroidv2.data.RetrofitClientInstance
import com.example.testesantanderandroidv2.model.StatementResponse
import com.example.testesantanderandroidv2.model.UserResponse
import com.example.testesantanderandroidv2.utils.Constants.Companion.API_PASSWORD
import com.example.testesantanderandroidv2.utils.Constants.Companion.API_USER
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BankAppRepository {

    private val service: CallApi = RetrofitClientInstance.getRetrofitInstance()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun getError(): LiveData<String> {
        return error
    }

    fun getStatement(idUser: String): MutableLiveData<StatementResponse> {
        val statementResponseMutableLiveData: MutableLiveData<StatementResponse> = MutableLiveData()
        service.getStatementList(idUser).enqueue(object : Callback<StatementResponse> {
            override fun onResponse(call: Call<StatementResponse>, response: Response<StatementResponse>) {
                if (response.isSuccessful) {
                    statementResponseMutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<StatementResponse>, t: Throwable) {}
        })
        return statementResponseMutableLiveData
    }

    fun getUser(): MutableLiveData<UserResponse> {
        val userResponseMutableLiveData: MutableLiveData<UserResponse> = MutableLiveData()
        service.getUserAccount(API_USER, API_PASSWORD).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
//                    val userResponse = response.body()
//                    val user = userResponse!!.userAccount
                    userResponseMutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {}
        })
        return userResponseMutableLiveData
    }

    object BankAppRepositoryProvider {
        fun providerBankAppRepository(): BankAppRepository {
            return BankAppRepository()
        }
    }
}