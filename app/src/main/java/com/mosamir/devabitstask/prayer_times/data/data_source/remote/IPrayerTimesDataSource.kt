package com.mosamir.devabitstask.prayer_times.data.data_source.remote

import com.mosamir.devabitstask.prayer_times.domain.model.PrayerTimes
import com.mosamir.devabitstask.util.IResult

interface IPrayerTimesDataSource {

    suspend fun getPrayerTimes(
        date: String,
        latitude: Double,
        longitude: Double,
        method: Int
    ): IResult<PrayerTimes>

}