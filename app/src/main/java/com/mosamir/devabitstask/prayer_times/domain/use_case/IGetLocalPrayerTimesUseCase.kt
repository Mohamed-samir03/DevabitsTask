package com.mosamir.devabitstask.prayer_times.domain.use_case

import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity
import com.mosamir.devabitstask.util.IResult

interface IGetLocalPrayerTimesUseCase {
    suspend fun getLocalPrayerTimes(): IResult<List<TimingsEntity>>
}