package com.example.testesantanderandroidv2.model

import com.example.testesantanderandroidv2.utils.Constants.Companion.STATEMENT_LIST_DATE
import com.example.testesantanderandroidv2.utils.Constants.Companion.STATEMENT_LIST_DESCRIPTION
import com.example.testesantanderandroidv2.utils.Constants.Companion.STATEMENT_LIST_TITLE
import com.example.testesantanderandroidv2.utils.Constants.Companion.STATEMENT_LIST_VALUE
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.*

class Statement(@SerializedName(STATEMENT_LIST_TITLE) @Expose var title: String,
                @SerializedName(STATEMENT_LIST_DATE) @Expose var date: Date,
                @SerializedName(STATEMENT_LIST_DESCRIPTION) @Expose var desc: String,
                @SerializedName(STATEMENT_LIST_VALUE) @Expose var value: BigDecimal)
