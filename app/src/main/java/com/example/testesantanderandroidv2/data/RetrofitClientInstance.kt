package com.example.testesantanderandroidv2.data

import com.example.testesantanderandroidv2.utils.Constants.Companion.API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {
    companion object {
        fun getRetrofitInstance(): CallApi {
            val retrofit = Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(CallApi::class.java)
        }
    }
}