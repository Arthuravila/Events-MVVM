package com.app.desafiosicredi.core.utils

interface Mapper<S, T> {
    fun map(source: S): T
}