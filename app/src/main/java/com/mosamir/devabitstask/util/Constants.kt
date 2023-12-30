package com.mosamir.devabitstask.util

import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity

class Constants {

    companion object {

        const val BASE_URL = "https://api.aladhan.com/"

        const val CHANNEL_ID = "ADHAN_CHANNEL"
        const val CHANNEL_NAME = "adhan channel"

        const val DEVABITS_DATABASE = "devabits"
        const val REGISTER_PRAYERS = "REGISTER_PRAYERS"

        const val PERMISSIONS_REQUEST_LOCATION = 1000
        const val PRAYER_TIMES_METHOD = 5

        lateinit var prayerTimes:List<TimingsEntity>

    }

}