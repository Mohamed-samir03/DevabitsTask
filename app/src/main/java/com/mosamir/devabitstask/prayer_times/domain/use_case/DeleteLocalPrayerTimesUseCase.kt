package com.mosamir.devabitstask.prayer_times.domain.use_case

import com.mosamir.devabitstask.prayer_times.domain.repository.IPrayerTimesRepo
import javax.inject.Inject

class DeleteLocalPrayerTimesUseCase @Inject constructor(
    private val iPrayerTimesRepo: IPrayerTimesRepo
): IDeleteLocalPrayerTimesUseCase {
    override suspend fun deletePrayerTimes() {
        iPrayerTimesRepo.deletePrayerTimes()
    }
}