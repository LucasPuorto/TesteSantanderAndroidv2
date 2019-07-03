package com.example.testesantanderandroidv2.data

import com.example.testesantanderandroidv2.model.StatementResponse
import com.example.testesantanderandroidv2.model.UserResponse
import com.example.testesantanderandroidv2.utils.Constants.Companion.API_PARAM_STATEMENT
import com.example.testesantanderandroidv2.utils.Constants.Companion.API_PARAM_USER
import com.example.testesantanderandroidv2.utils.Constants.Companion.API_PASSWORD
import com.example.testesantanderandroidv2.utils.Constants.Companion.API_USER
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CallApi {

    @GET(API_PARAM_STATEMENT)
    fun getStatement(): Call<StatementResponse>

    @FormUrlEncoded
    @POST(API_PARAM_USER)
    fun getUser(@Field(API_USER) user: String, @Field(API_PASSWORD) password: String): Call<UserResponse>
}
