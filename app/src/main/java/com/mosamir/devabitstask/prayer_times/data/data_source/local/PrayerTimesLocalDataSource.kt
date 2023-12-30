package com.mosamir.devabitstask.prayer_times.data.data_source.local

import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity
import com.mosamir.devabitstask.prayer_times.data.data_source.mapper.asDomain
import com.mosamir.devabitstask.util.IResult
import com.mosamir.devabitstask.util.NetworkState
import java.lang.Exception
import javax.inject.Inject

class PrayerTimesLocalDataSource @Inject constructor(
    private val prayerTimesDao: PrayerTimesDao
) : IPrayerTimesLocalDataSource {

    override suspend fun insertPrayerTimes(timings: TimingsEntity) {
        prayerTimesDao.insertTimings(timings)
    }

    override suspend fun getAllPrayerTimes(): IResult<List<TimingsEntity>> {
        return try {
            val prayerTimes = prayerTimesDao.getAllTimings()
            if (!prayerTimes.isNullOrEmpty()){
                return IResult.onSuccess(prayerTimes)
            }else{
                return IResult.onFail("fail")
            }
        }catch (e: Exception){
            IResult.onFail(e.message.toString())
        }
    }

    override suspend fun deletePrayerTimes() {
        prayerTimesDao.deleteTimings()
    }

}