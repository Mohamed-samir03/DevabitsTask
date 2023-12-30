package com.mosamir.devabitstask.util

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class PrayerTimesWorker(val appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {

        val prayerTimes = Constants.prayerTimes

        if (prayerTimes.isNotEmpty()){
            for (times in prayerTimes){
                for (day in appContext.timingsToArrayList(times)){
                    val delayTime = appContext.getMillisDifference(day,times.date)
                    if (delayTime > 0){
                        val workRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
                            .addTag(day + times.date)
                            .setInitialDelay(delayTime,TimeUnit.MILLISECONDS)
                            .build()
                        WorkManager.getInstance(applicationContext).enqueue(workRequest)
                    }
                }
            }
        }

        return Result.success()
    }

}