package com.mosamir.devabitstask.prayer_times.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity

@Dao
interface PrayerTimesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimings(timings:TimingsEntity)

    @Query("DELETE FROM PrayerTimes")
    suspend fun deleteTimings()

    @Query("SELECT * FROM PrayerTimes")
    suspend fun getAllTimings(): List<TimingsEntity>

}