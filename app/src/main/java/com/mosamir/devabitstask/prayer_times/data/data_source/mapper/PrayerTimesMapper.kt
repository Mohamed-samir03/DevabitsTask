package com.mosamir.devabitstask.prayer_times.data.data_source.mapper

import com.mosamir.devabitstask.prayer_times.data.model.PrayerTimesResponse
import com.mosamir.devabitstask.prayer_times.domain.model.PrayerTimes

fun PrayerTimesResponse.asDomain() = data?.asDomain()?.let {
    PrayerTimes(
        code = code,
        data = it,
        status = status
    )
}