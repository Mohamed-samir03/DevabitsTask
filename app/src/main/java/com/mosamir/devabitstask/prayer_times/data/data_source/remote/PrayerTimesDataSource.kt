package com.mosamir.devabitstask.prayer_times.data.data_source.remote

import com.mosamir.devabitstask.prayer_times.data.data_source.mapper.asDomain
import com.mosamir.devabitstask.prayer_times.domain.model.PrayerTimes
import com.mosamir.devabitstask.util.IResult
import com.mosamir.devabitstask.util.NetworkState
import java.lang.Exception
import javax.inject.Inject

class PrayerTimesDataSource @Inject constructor(
    private val apiService: ApiService
) : IPrayerTimesDataSource {

    override suspend fun getPrayerTimes(
        date: String,
        latitude: Double,
        longitude: Double,
        method: Int
    ): IResult<PrayerTimes> {
        return try {
            val prayerTimes = apiService.getPrayerTimes(date, latitude, longitude, method)
            if (prayerTimes.status == "OK"){
                return IResult.onSuccess(prayerTimes.asDomain()!!)
            }else{
                return IResult.onFail("fail")
            }
        }catch (e: Exception){
            IResult.onFail(NetworkState.getErrorMessage(e).msg.toString())
        }
    }

}