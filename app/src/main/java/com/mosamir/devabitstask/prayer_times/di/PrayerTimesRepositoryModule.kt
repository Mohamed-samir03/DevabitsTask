package com.mosamir.devabitstask.prayer_times.di

import com.mosamir.devabitstask.prayer_times.data.data_source.local.IPrayerTimesLocalDataSource
import com.mosamir.devabitstask.prayer_times.data.data_source.remote.IPrayerTimesDataSource
import com.mosamir.devabitstask.prayer_times.data.repository.PrayerTimesRepo
import com.mosamir.devabitstask.prayer_times.domain.repository.IPrayerTimesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object PrayerTimesRepositoryModule {


    @Provides
    fun providePrayerTimesRepo(
        iPrayerTimesDataSource: IPrayerTimesDataSource,
        iPrayerTimesLocalDataSource: IPrayerTimesLocalDataSource
        ):IPrayerTimesRepo
            = PrayerTimesRepo(iPrayerTimesDataSource,iPrayerTimesLocalDataSource)


}