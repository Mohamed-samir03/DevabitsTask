package com.mosamir.devabitstask.prayer_times.data.model

data class PrayerTimesResponse(
    val code: Int,
    val `data`: RemoteData?,
    val status: String
)