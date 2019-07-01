package com.example.testesantanderandroidv2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLoginClick()
    }

    private fun btLoginClick() {
        activity_login_bt_login.setOnClickListener {
            val intent = Intent(this, StatementActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}