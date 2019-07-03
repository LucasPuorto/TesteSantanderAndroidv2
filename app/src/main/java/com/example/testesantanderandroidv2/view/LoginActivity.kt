package com.example.testesantanderandroidv2.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.testesantanderandroidv2.R
import com.example.testesantanderandroidv2.utils.CPFUtil
import com.example.testesantanderandroidv2.utils.isValidEmail
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLoginClick()
    }

    private fun btLoginClick() {
        activity_login_bt_login.setOnClickListener {
            validateLoginUser()
            validateLoginPassword()
        }
    }

    private fun validateLoginUser() {
        val inputUser: String = activity_login_et_user.text.toString()
        val testValidUser = inputUser.isValidEmail()
        if (testValidUser || CPFUtil.myValidateCPF(inputUser)) {
            callStatementActivity()
        } else {
            Toast.makeText(this, "Usuário invalido", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateLoginPassword() {
        
    }

    private fun callStatementActivity() {
        val intent = Intent(this, StatementActivity::class.java)
        startActivity(intent)
        finish()
    }
}
