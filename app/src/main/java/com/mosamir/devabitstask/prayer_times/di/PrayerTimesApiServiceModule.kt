package com.mosamir.devabitstask.prayer_times.di

import com.mosamir.devabitstask.prayer_times.data.data_source.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object PrayerTimesApiServiceModule {

    @Provides
    fun provideApiService(retrofit: Retrofit):ApiService
            = retrofit.create(ApiService::class.java)

}