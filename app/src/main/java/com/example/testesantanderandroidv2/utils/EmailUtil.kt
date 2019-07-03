package com.example.testesantanderandroidv2.utils

import android.util.Patterns

fun String.isValidEmail(): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(this).matches()
