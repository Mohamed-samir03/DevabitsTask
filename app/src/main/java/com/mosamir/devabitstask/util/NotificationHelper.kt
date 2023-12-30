package com.mosamir.devabitstask.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.mosamir.devabitstask.R
import com.mosamir.devabitstask.prayer_times.presentation.common.MainActivity

class NotificationHelper(private val context: Context) {

    @SuppressLint("NotificationPermission")
    fun sendNotification(title: String, content: String, sound: Uri) {
        val manager = context
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = createNotificationBuilder(title, content, sound)
        createNotificationChannel(manager, sound)

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        notificationBuilder.setContentIntent(pendingIntent)

        manager.notify(0, notificationBuilder.build())
    }

    private fun createNotificationBuilder(
        title: String,
        content: String,
        sound: Uri
    ): NotificationCompat.Builder {
        val notificationBuilder = NotificationCompat.Builder(
            context, Constants.CHANNEL_ID
        )
        notificationBuilder
            .setSmallIcon(R.drawable.pray)
            .setContentTitle(title)
            .setSound(sound)
            .setDefaults(NotificationCompat.DEFAULT_SOUND)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        return notificationBuilder
    }

    private fun createNotificationChannel(manager: NotificationManager, sound: Uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                Constants.CHANNEL_ID,
                Constants.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            notificationChannel.setSound(sound, audioAttributes)
            manager.createNotificationChannel(notificationChannel)
        }
    }
}
