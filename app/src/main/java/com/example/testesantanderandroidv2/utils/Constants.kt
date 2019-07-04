package com.example.testesantanderandroidv2.utils

class Constants {
    companion object {
        const val API_URL = "https://bank-app-test.herokuapp.com/api/"
        const val API_PARAM_STATEMENT = "statements/"
        const val API_PARAM_USER = "login"
        const val API_USER = "user"
        const val API_PASSWORD = "password"

        const val SERIALIZABLE_STATEMENT_LIST = "statementList"
        const val STATEMENT_LIST_TITLE = "title"
        const val STATEMENT_LIST_DATE = "date"
        const val STATEMENT_LIST_DESCRIPTION = "desc"
        const val STATEMENT_LIST_VALUE = "value"

        const val SERIALIZABLE_USER = "user"

        const val SERIALIZABLE_USER_ACCOUNT = "userAccount"
        const val USER_ACCOUNT_USER_ID = "userId"
        const val USER_ACCOUNT_NAME = "name"
        const val USER_ACCOUNT_BANK_ACCOUNT = "bankAccount"
        const val USER_ACCOUNT_AGENCY = "agency"
        const val USER_ACCOUNT_BALANCE = "balance"

        const val DATE_FORMAT_BR = "dd/MM/yyyy"

        const val PATTERN_SPECIAL_CHARACTERS = "[$&+,:;=?@#|'<>.-^*()%!]"
        const val PATTERN_UPPER_CASE = "[A-Z]"
        const val PATTERN_NUMBER = "[0-9]"
    }
}