package com.mosamir.devabitstask.prayer_times.domain.use_case

import com.mosamir.devabitstask.prayer_times.domain.model.PrayerTimes
import com.mosamir.devabitstask.util.IResult

interface IGetPrayerTimesUseCase {

    suspend fun getPrayerTimes(
        date: String,
        latitude: Double,
        longitude: Double,
        method: Int
    ): IResult<PrayerTimes>

}