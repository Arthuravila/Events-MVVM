package com.app.desafiosicredi.core.utils

import android.content.Context
import android.content.Intent
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

val brazilianLocale = Locale("pt", "BR")

fun setCurrency(price: Double?): String? {
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    format.currency = Currency.getInstance("BRL")
    return if (price != null) format.format(price)
    else ""
}

fun formatDate(date: Date?): String? {
    val simpleDateFormatDate = SimpleDateFormat("dd/MM/yyyy HH:mm", brazilianLocale)
    return if (date != null) simpleDateFormatDate.format(date)
    else ""
}

fun getWeekDay(date: Date?): String? {
    if (date != null) {
        val simpleDateFormatWeekDayOfWeek = SimpleDateFormat("EEEE", brazilianLocale)
        return simpleDateFormatWeekDayOfWeek.format(date).capitalize()
    }
    return ""
}

fun openShareDialog(context: Context, message: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            message
        )
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}
