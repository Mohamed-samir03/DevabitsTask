package com.mosamir.devabitstask.prayer_times.data.data_source.mapper

import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity
import com.mosamir.devabitstask.prayer_times.domain.model.Timings

fun  Timings.toRoomEntity(date:String) = TimingsEntity(
    date = date,
    Fajr = Fajr,
    Sunrise = Sunrise,
    Dhuhr = Dhuhr,
    Asr = Asr,
    Maghrib = Maghrib,
    Isha = Isha
)