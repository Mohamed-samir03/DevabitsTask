package com.mosamir.devabitstask.prayer_times.domain.repository

import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity
import com.mosamir.devabitstask.prayer_times.domain.model.PrayerTimes
import com.mosamir.devabitstask.util.IResult

interface IPrayerTimesRepo {

    suspend fun getPrayerTimes(
        date: String,
        latitude: Double,
        longitude: Double,
        method: Int
    ): IResult<PrayerTimes>

    suspend fun getAllPrayerTimes(): IResult<List<TimingsEntity>>

    suspend fun deletePrayerTimes()

}