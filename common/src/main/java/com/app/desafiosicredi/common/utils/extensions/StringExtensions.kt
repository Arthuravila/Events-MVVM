package com.app.desafiosicredi.common.utils.extensions

import android.text.TextUtils

fun String.verifyFullName(): Boolean =
    if (!TextUtils.isEmpty(this)) {
        this.split(" ").size > 1
    } else {
        false
    }

fun String.verifyEmail(): Boolean =
    androidx.core.util.PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
