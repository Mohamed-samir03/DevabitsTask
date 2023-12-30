package com.mosamir.devabitstask.prayer_times.data.data_source.remote

import com.mosamir.devabitstask.prayer_times.data.model.PrayerTimesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v1/timings/{date}")
    suspend fun getPrayerTimes(
        @Path("date") date: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("method") method: Int
    ): PrayerTimesResponse

}