package com.mosamir.devabitstask.util

import android.content.Context
import android.net.Uri
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mosamir.devabitstask.R


class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val soundUri: Uri = Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.adhan)
        val notificationHelper = NotificationHelper(applicationContext)
        notificationHelper.sendNotification("Devabits", "حان الان ميقات الصلاة",soundUri)
        return Result.success()
    }

}
