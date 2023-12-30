package com.mosamir.devabitstask.prayer_times.data.repository

import android.util.Log
import com.mosamir.devabitstask.prayer_times.data.data_source.local.IPrayerTimesLocalDataSource
import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity
import com.mosamir.devabitstask.prayer_times.data.data_source.mapper.toRoomEntity
import com.mosamir.devabitstask.prayer_times.data.data_source.remote.IPrayerTimesDataSource
import com.mosamir.devabitstask.prayer_times.domain.model.PrayerTimes
import com.mosamir.devabitstask.prayer_times.domain.repository.IPrayerTimesRepo
import com.mosamir.devabitstask.util.IResult
import com.mosamir.devabitstask.util.getData
import java.lang.Exception
import javax.inject.Inject

class PrayerTimesRepo @Inject constructor(
    private val iPrayerTimesDataSource: IPrayerTimesDataSource,
    private val iPrayerTimesLocalDataSource: IPrayerTimesLocalDataSource
):IPrayerTimesRepo{
    override suspend fun getPrayerTimes(
        date: String,
        latitude: Double,
        longitude: Double,
        method: Int
    ): IResult<PrayerTimes> {
        val remoteData = iPrayerTimesDataSource.getPrayerTimes(date, latitude, longitude, method)
        try {
            iPrayerTimesLocalDataSource.insertPrayerTimes(remoteData.getData()?.data?.timings?.toRoomEntity(date)!!)
        }catch (ex:Exception){
            Log.e("PrayerTimesRepo", "Error to insert data: ${ex.message}")
        }
        return remoteData
    }

    override suspend fun getAllPrayerTimes(): IResult<List<TimingsEntity>> {
        return iPrayerTimesLocalDataSource.getAllPrayerTimes()
    }

    override suspend fun deletePrayerTimes() {
        iPrayerTimesLocalDataSource.deletePrayerTimes()
    }
}