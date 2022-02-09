package com.app.desafiosicredi.common.utils

interface Mapper<S, T> {
    fun map(source: S): T
}