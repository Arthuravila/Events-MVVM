package com.app.desafiosicredi.core.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun setCurrency(price: Double?): String? {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance("BRL")
    return if (price != null) format.format(price)
    else ""
}

fun formatDate(date: Date?): String? {
    val brazilianLocale = Locale("pt", "BR")
    val simpleDateFormatDate = SimpleDateFormat("dd/MM/yyyy", brazilianLocale)
    return if (date != null) simpleDateFormatDate.format(date)
    else ""
}