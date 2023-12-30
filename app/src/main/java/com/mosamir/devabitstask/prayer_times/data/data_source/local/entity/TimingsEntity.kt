package com.mosamir.devabitstask.prayer_times.data.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PrayerTimes")
data class TimingsEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val date:String,
    val Fajr: String,
    val Sunrise: String,
    val Dhuhr: String,
    val Asr: String,
    val Maghrib: String,
    val Isha: String
)