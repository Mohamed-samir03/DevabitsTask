package com.mosamir.devabitstask.prayer_times.domain.use_case

import com.mosamir.devabitstask.prayer_times.domain.model.PrayerTimes
import com.mosamir.devabitstask.prayer_times.domain.repository.IPrayerTimesRepo
import com.mosamir.devabitstask.util.IResult
import javax.inject.Inject

class GetPrayerTimesUseCase @Inject constructor(
    private val iPrayerTimesRepo: IPrayerTimesRepo
):IGetPrayerTimesUseCase {

    override suspend fun getPrayerTimes(
        date: String,
        latitude: Double,
        longitude: Double,
        method: Int
    ): IResult<PrayerTimes> {
        return iPrayerTimesRepo.getPrayerTimes(date, latitude, longitude, method)
    }

}