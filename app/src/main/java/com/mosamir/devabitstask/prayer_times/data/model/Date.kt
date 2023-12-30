package com.mosamir.devabitstask.prayer_times.data.model

data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)