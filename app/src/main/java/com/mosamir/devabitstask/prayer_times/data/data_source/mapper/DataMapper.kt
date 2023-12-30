package com.mosamir.devabitstask.prayer_times.data.data_source.mapper

import com.mosamir.devabitstask.prayer_times.data.model.RemoteData
import com.mosamir.devabitstask.prayer_times.domain.model.Data

fun RemoteData.asDomain() = Data(
    timings = timings.asDomain()
)