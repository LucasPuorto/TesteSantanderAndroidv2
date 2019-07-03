package com.example.testesantanderandroidv2.extension

import com.example.testesantanderandroidv2.utils.Constants.Companion.DATE_FORMAT_BR
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
    val format = SimpleDateFormat(DATE_FORMAT_BR)
    return format.format(date)
}

