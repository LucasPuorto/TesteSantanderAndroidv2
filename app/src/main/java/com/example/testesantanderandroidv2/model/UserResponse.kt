package com.example.testesantanderandroidv2.model

import com.example.testesantanderandroidv2.utils.Constants.Companion.SERIALIZABLE_USER_ACCOUNT
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserResponse(
        @SerializedName(SERIALIZABLE_USER_ACCOUNT) val userAccount: User) : Serializable