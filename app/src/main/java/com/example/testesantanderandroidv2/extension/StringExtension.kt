package com.example.testesantanderandroidv2.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun formatForBrazilianCurrency(value: BigDecimal): String {
    val brazilianFormat = DecimalFormat
            .getCurrencyInstance(Locale("pt", "br"))
    return brazilianFormat.format(value)
}

fun formatForBrazilianDate(date: Date): String {
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(date)
}