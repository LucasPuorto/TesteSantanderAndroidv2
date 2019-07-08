package com.example.testesantanderandroidv2.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.testesantanderandroidv2.R
import com.example.testesantanderandroidv2.model.UserResponse
import com.example.testesantanderandroidv2.utils.CPFUtil
import com.example.testesantanderandroidv2.utils.Constants.Companion.PATTERN_NUMBER
import com.example.testesantanderandroidv2.utils.Constants.Companion.PATTERN_SPECIAL_CHARACTERS
import com.example.testesantanderandroidv2.utils.Constants.Companion.PATTERN_UPPER_CASE
import com.example.testesantanderandroidv2.utils.Constants.Companion.USER
import com.example.testesantanderandroidv2.utils.isValidEmail
import com.example.testesantanderandroidv2.viewmodel.BankAppViewModel
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var bankAppViewModel: BankAppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bankAppViewModel = ViewModelProviders.of(this)[BankAppViewModel::class.java]

        btLoginClick()
    }

    private fun btLoginClick() {
        activity_login_bt_login.setOnClickListener {
            if (validateLoginUser()) {
                if (validateLoginPassword()) {
                    setUserToStatementActivity()
                }
            }
        }
    }

    private fun validateLoginUser(): Boolean {
        val inputUser: String = activity_login_et_user.text.toString()
        val testValidUser = inputUser.isValidEmail()
        return if (testValidUser || CPFUtil.myValidateCPF(inputUser)) {
            true
        } else {
            Toast.makeText(this, getString(R.string.invalid_user), Toast.LENGTH_LONG).show()
            false
        }
    }

    private fun validateLoginPassword(): Boolean {
        val inputPassword: String = activity_login_et_password.text.toString()
        val upperCase: Pattern = Pattern.compile(PATTERN_UPPER_CASE)
        val specialChar: Pattern = Pattern.compile(PATTERN_SPECIAL_CHARACTERS)
        val number: Pattern = Pattern.compile(PATTERN_NUMBER)
        return if (upperCase.matcher(inputPassword).find() &&
                specialChar.matcher(inputPassword).find() &&
                number.matcher(inputPassword).find()) {
            true
        } else {
            Toast.makeText(this, getString(R.string.password_without_minimum_requirements), Toast.LENGTH_LONG).show()
            false
        }
    }

    private fun setUserToStatementActivity() {
        bankAppViewModel.setUser()
        bankAppViewModel.userResponseObservable().observe(this, Observer {
            if (it != null) {
                callStatementActivity(it)
            }
        })
    }

    private fun callStatementActivity(userResponse: UserResponse) {
        val intent = Intent(this, StatementActivity::class.java)
        intent.putExtra(USER, userResponse.userAccount)
        startActivity(intent)
        finish()
    }
}
