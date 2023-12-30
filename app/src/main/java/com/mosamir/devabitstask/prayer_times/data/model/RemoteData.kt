package com.mosamir.devabitstask.prayer_times.data.model

data class RemoteData(
    val date: Date,
    val meta: Meta,
    val timings: RemoteTimings
)