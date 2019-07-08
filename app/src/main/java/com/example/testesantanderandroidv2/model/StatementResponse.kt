package com.example.testesantanderandroidv2.model

import com.example.testesantanderandroidv2.utils.Constants.Companion.STATEMENT_LIST
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class StatementResponse(
        @SerializedName(STATEMENT_LIST) @Expose val statementList: ArrayList<Statement>) : Serializable
