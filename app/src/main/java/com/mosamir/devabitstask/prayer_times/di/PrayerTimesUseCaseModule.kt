package com.mosamir.devabitstask.prayer_times.di

import com.mosamir.devabitstask.prayer_times.domain.repository.IPrayerTimesRepo
import com.mosamir.devabitstask.prayer_times.domain.use_case.DeleteLocalPrayerTimesUseCase
import com.mosamir.devabitstask.prayer_times.domain.use_case.GetLocalPrayerTimesUseCase
import com.mosamir.devabitstask.prayer_times.domain.use_case.GetPrayerTimesUseCase
import com.mosamir.devabitstask.prayer_times.domain.use_case.IDeleteLocalPrayerTimesUseCase
import com.mosamir.devabitstask.prayer_times.domain.use_case.IGetLocalPrayerTimesUseCase
import com.mosamir.devabitstask.prayer_times.domain.use_case.IGetPrayerTimesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object PrayerTimesUseCaseModule {


    @Provides
    fun provideGetPrayerTimesUseCase(iPrayerTimesRepo: IPrayerTimesRepo):IGetPrayerTimesUseCase
            = GetPrayerTimesUseCase(iPrayerTimesRepo)

    @Provides
    fun provideGetLocalPrayerTimesUseCase(iPrayerTimesRepo: IPrayerTimesRepo):IGetLocalPrayerTimesUseCase
            = GetLocalPrayerTimesUseCase(iPrayerTimesRepo)

    @Provides
    fun provideDeleteLocalPrayerTimesUseCase(iPrayerTimesRepo: IPrayerTimesRepo):IDeleteLocalPrayerTimesUseCase
            = DeleteLocalPrayerTimesUseCase(iPrayerTimesRepo)


}