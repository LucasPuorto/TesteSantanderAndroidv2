package com.example.testesantanderandroidv2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_statement.*

class StatementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)

        activity_statement_tv_name.text = "Lucas Puorto Catarino"
        activity_statement_tv_account.text = "2050 / 01.111222-4"
        activity_statement_tv_balance.text = "R$ 1.000,00"

        btLogoutClick()
        setRecyclerView()
    }

    private fun btLogoutClick() {
        activity_statement_bt_logout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setRecyclerView() {
        val recyclerView = activity_statement_rv_recent
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val statementAdapter = StatementAdapter(this)
        recyclerView.adapter = statementAdapter
    }
}