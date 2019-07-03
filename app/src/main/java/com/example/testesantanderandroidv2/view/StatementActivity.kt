package com.example.testesantanderandroidv2.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.testesantanderandroidv2.R
import com.example.testesantanderandroidv2.adapter.StatementAdapter
import com.example.testesantanderandroidv2.data.CallApi
import com.example.testesantanderandroidv2.data.RetrofitClientInstance
import com.example.testesantanderandroidv2.extension.formatForBrazilianCurrency
import com.example.testesantanderandroidv2.model.Statement
import com.example.testesantanderandroidv2.model.StatementResponse
import com.example.testesantanderandroidv2.model.User
import com.example.testesantanderandroidv2.model.UserResponse
import com.example.testesantanderandroidv2.utils.Constants.Companion.API_PASSWORD
import com.example.testesantanderandroidv2.utils.Constants.Companion.API_USER
import kotlinx.android.synthetic.main.activity_statement.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class StatementActivity : AppCompatActivity() {

    private lateinit var statementList: ArrayList<Statement>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)

        btLogoutClick()
        recoverUser()
        recoverStatementList()
    }

    private fun btLogoutClick() {
        activity_statement_bt_logout.setOnClickListener {
            logoutAlertDialog()
        }
    }

    private fun logoutAlertDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(getString(R.string.alertDetailsLogoutTitle))
        alertDialog.setMessage(getString(R.string.alertDetailsLogoutMessage))
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton(getString(R.string.yes)) { _, _ ->
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        alertDialog.setNegativeButton(getString(R.string.no)) { _, _ ->

        }
        val dialog: AlertDialog = alertDialog.create()
        dialog.show()
    }

    private fun recoverUser() {
        val service: CallApi = RetrofitClientInstance.getRetrofitInstance()
        service.getUser(API_USER, API_PASSWORD).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val user = userResponse!!.userAccount
                    setUserInformation(user)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {}
        })
    }

    private fun setUserInformation(user: User) {
        activity_statement_tv_name.text = user.name
        activity_statement_tv_account.text = (user.bankAccount + " / " + user.agency)
        activity_statement_tv_balance.text = formatForBrazilianCurrency(user.balance)
    }

    private fun recoverStatementList() {
        val service: CallApi = RetrofitClientInstance.getRetrofitInstance()
        service.getStatement().enqueue(object : Callback<StatementResponse> {
            override fun onResponse(call: Call<StatementResponse>, response: Response<StatementResponse>) {
                if (response.isSuccessful) {
                    val statementResponse = response.body()
                    statementList = statementResponse!!.statementList
                    setRecyclerView()
                }
            }

            override fun onFailure(call: Call<StatementResponse>, t: Throwable) {}
        })
    }

    private fun setRecyclerView() {
        val recyclerView = activity_statement_rv_recent
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val statementAdapter = StatementAdapter(this, statementList)
        recyclerView.adapter = statementAdapter
    }
}