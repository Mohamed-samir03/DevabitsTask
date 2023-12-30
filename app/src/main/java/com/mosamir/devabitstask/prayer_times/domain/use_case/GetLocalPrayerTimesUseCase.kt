package com.mosamir.devabitstask.prayer_times.domain.use_case

import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity
import com.mosamir.devabitstask.prayer_times.domain.repository.IPrayerTimesRepo
import com.mosamir.devabitstask.util.IResult
import javax.inject.Inject

class GetLocalPrayerTimesUseCase@Inject constructor(
    private val iPrayerTimesRepo: IPrayerTimesRepo
):IGetLocalPrayerTimesUseCase {
    override suspend fun getLocalPrayerTimes(): IResult<List<TimingsEntity>> {
        return iPrayerTimesRepo.getAllPrayerTimes()
    }
}