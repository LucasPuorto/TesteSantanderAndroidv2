package com.example.testesantanderandroidv2.model

import com.example.testesantanderandroidv2.utils.Constants.Companion.USER_ACCOUNT_AGENCY
import com.example.testesantanderandroidv2.utils.Constants.Companion.USER_ACCOUNT_BALANCE
import com.example.testesantanderandroidv2.utils.Constants.Companion.USER_ACCOUNT_BANK_ACCOUNT
import com.example.testesantanderandroidv2.utils.Constants.Companion.USER_ACCOUNT_NAME
import com.example.testesantanderandroidv2.utils.Constants.Companion.USER_ACCOUNT_USER_ID
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal

class User(@SerializedName(USER_ACCOUNT_USER_ID) val userId: Int,
           @SerializedName(USER_ACCOUNT_NAME) val name: String,
           @SerializedName(USER_ACCOUNT_BANK_ACCOUNT) val bankAccount: String,
           @SerializedName(USER_ACCOUNT_AGENCY) val agency: String,
           @SerializedName(USER_ACCOUNT_BALANCE) val balance: BigDecimal) : Serializable