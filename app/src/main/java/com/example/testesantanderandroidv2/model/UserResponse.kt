package com.example.testesantanderandroidv2.model

import com.example.testesantanderandroidv2.utils.Constants.Companion.USER_ACCOUNT
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserResponse(
        @SerializedName(USER_ACCOUNT) val userAccount: User) : Serializable