package com.mosamir.devabitstask.prayer_times.data.data_source.local

import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity
import com.mosamir.devabitstask.util.IResult


interface IPrayerTimesLocalDataSource {

    suspend fun insertPrayerTimes(timings: TimingsEntity)
    suspend fun getAllPrayerTimes(): IResult<List<TimingsEntity>>
    suspend fun deletePrayerTimes()

}