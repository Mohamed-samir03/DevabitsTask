package com.mosamir.devabitstask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mosamir.devabitstask.prayer_times.data.data_source.local.PrayerTimesDao
import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity

@Database(entities = [TimingsEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase() {
    abstract fun prayerTimesDao():PrayerTimesDao
}
