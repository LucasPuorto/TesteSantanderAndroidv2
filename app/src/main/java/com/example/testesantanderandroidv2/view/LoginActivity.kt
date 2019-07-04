package com.example.testesantanderandroidv2.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.testesantanderandroidv2.R
import com.example.testesantanderandroidv2.data.CallApi
import com.example.testesantanderandroidv2.data.RetrofitClientInstance
import com.example.testesantanderandroidv2.model.User
import com.example.testesantanderandroidv2.model.UserResponse
import com.example.testesantanderandroidv2.utils.CPFUtil
import com.example.testesantanderandroidv2.utils.Constants
import com.example.testesantanderandroidv2.utils.Constants.Companion.API_PASSWORD
import com.example.testesantanderandroidv2.utils.Constants.Companion.API_USER
import com.example.testesantanderandroidv2.utils.Constants.Companion.PATTERN_NUMBER
import com.example.testesantanderandroidv2.utils.Constants.Companion.PATTERN_SPECIAL_CHARACTERS
import com.example.testesantanderandroidv2.utils.Constants.Companion.PATTERN_UPPER_CASE
import com.example.testesantanderandroidv2.utils.Constants.Companion.SERIALIZABLE_USER
import com.example.testesantanderandroidv2.utils.isValidEmail
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLoginClick()
    }

    private fun btLoginClick() {
        activity_login_bt_login.setOnClickListener {
            //            if (validateLoginUser()) {
//                if (validateLoginPassword()) {
//                    recoverUser()
//                }
//            }
            recoverUser()
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

    private fun recoverUser() {
        val service: CallApi = RetrofitClientInstance.getRetrofitInstance()
        service.getUserAccount(API_USER, API_PASSWORD).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    val user = userResponse!!.userAccount
                    callStatementActivity(user)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {}
        })
    }

    private fun callStatementActivity(user: User) {
        val intent = Intent(this, StatementActivity::class.java)
        intent.putExtra(SERIALIZABLE_USER, user)
        startActivity(intent)
        finish()
    }
}
