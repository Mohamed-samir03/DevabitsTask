package com.mosamir.devabitstask.prayer_times.di

import com.mosamir.devabitstask.database.AppDatabase
import com.mosamir.devabitstask.prayer_times.data.data_source.local.IPrayerTimesLocalDataSource
import com.mosamir.devabitstask.prayer_times.data.data_source.local.PrayerTimesDao
import com.mosamir.devabitstask.prayer_times.data.data_source.local.PrayerTimesLocalDataSource
import com.mosamir.devabitstask.prayer_times.data.data_source.remote.ApiService
import com.mosamir.devabitstask.prayer_times.data.data_source.remote.IPrayerTimesDataSource
import com.mosamir.devabitstask.prayer_times.data.data_source.remote.PrayerTimesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object PrayerTimesDataSourceModule {

    @Provides
    fun getPrayerTimesDataSource(apiService: ApiService):IPrayerTimesDataSource
            = PrayerTimesDataSource(apiService)

    @Provides
    fun providePrayerTimesDao(databaseApp:AppDatabase): PrayerTimesDao = databaseApp.prayerTimesDao()

    @Provides
    fun providePrayerTimesLocalDataSource(
        prayerTimesDao: PrayerTimesDao
    ): IPrayerTimesLocalDataSource = PrayerTimesLocalDataSource(prayerTimesDao)

}