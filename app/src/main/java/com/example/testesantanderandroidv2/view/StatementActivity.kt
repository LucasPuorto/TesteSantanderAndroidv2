package com.example.testesantanderandroidv2.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.testesantanderandroidv2.R
import com.example.testesantanderandroidv2.adapter.StatementAdapter
import com.example.testesantanderandroidv2.data.CallApi
import com.example.testesantanderandroidv2.data.RetrofitClientInstance
import com.example.testesantanderandroidv2.extension.formatForBrazilianCurrency
import com.example.testesantanderandroidv2.model.Statement
import com.example.testesantanderandroidv2.model.StatementResponse
import com.example.testesantanderandroidv2.model.User
import com.example.testesantanderandroidv2.utils.Constants.Companion.SERIALIZABLE_USER
import kotlinx.android.synthetic.main.activity_statement.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatementActivity : AppCompatActivity() {

    private var idUser: String = ""
    private lateinit var statementList: ArrayList<Statement>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)

        setUserInformation()
        recoverStatementList()
        btLogoutClick()
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

    private fun setUserInformation() {
        val user = intent.extras.get(SERIALIZABLE_USER) as User

        idUser = user.userId.toString()
        activity_statement_tv_name.text = user.name
        activity_statement_tv_account_agency.text = (user.bankAccount + " / " + user.agency)
        activity_statement_tv_balance.text = formatForBrazilianCurrency(user.balance)
    }

    private fun recoverStatementList() {
        val service: CallApi = RetrofitClientInstance.getRetrofitInstance()
        service.getStatementList(idUser).enqueue(object : Callback<StatementResponse> {
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