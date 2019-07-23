package com.example.testesantanderandroidv2.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.testesantanderandroidv2.R
import com.example.testesantanderandroidv2.adapter.StatementAdapter
import com.example.testesantanderandroidv2.extension.formatForBrazilianCurrency
import com.example.testesantanderandroidv2.model.StatementResponse
import com.example.testesantanderandroidv2.model.User
import com.example.testesantanderandroidv2.utils.Constants.Companion.USER
import com.example.testesantanderandroidv2.viewmodel.BankAppViewModel
import kotlinx.android.synthetic.main.activity_statement.*

class StatementActivity : AppCompatActivity() {

    private lateinit var bankAppViewModel: BankAppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)

        bankAppViewModel = ViewModelProviders.of(this)[BankAppViewModel::class.java]
        bankAppViewModel.setUserParcelable(intent.getSerializableExtra(USER) as User)

        subscribe()
        btLogoutClick()
    }

    private fun subscribe() {
        bankAppViewModel.getUserObservable().observe(this, Observer {
            if (it != null) {
                setUserInformation(it)
                recoverStatementList(it.userId.toString())
            }
        })
    }

    private fun setUserInformation(user: User) {
        activity_statement_tv_name.text = user.name
        activity_statement_tv_account_agency.text = (user.bankAccount + " / " + user.agency)
        activity_statement_tv_balance.text = formatForBrazilianCurrency(user.balance)

    }

    private fun recoverStatementList(idUser: String) {
        bankAppViewModel.getStatement(idUser)
        bankAppViewModel.statementResponseObservable().observe(this, Observer {
            if (it != null) {
                setRecyclerView(it)
            }
        })
    }

    private fun setRecyclerView(statementResponse: StatementResponse) {
        val recyclerView = activity_statement_rv_recent
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val statementAdapter = StatementAdapter(this, statementResponse.statementList)
        recyclerView.adapter = statementAdapter
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
}