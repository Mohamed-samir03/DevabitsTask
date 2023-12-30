package com.mosamir.devabitstask.prayer_times.data.data_source.mapper

import com.mosamir.devabitstask.prayer_times.data.model.RemoteTimings
import com.mosamir.devabitstask.prayer_times.domain.model.Timings

fun RemoteTimings.asDomain() = Timings(
    Asr = Asr,
    Dhuhr = Dhuhr,
    Fajr = Fajr,
    Firstthird = Firstthird,
    Imsak = Imsak,
    Isha = Isha,
    Lastthird = Lastthird,
    Maghrib = Maghrib,
    Midnight = Midnight,
    Sunrise = Sunrise,
    Sunset = Sunset
)