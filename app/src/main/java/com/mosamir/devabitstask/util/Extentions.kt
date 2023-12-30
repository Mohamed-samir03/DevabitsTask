package com.mosamir.devabitstask.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun AppCompatActivity.showToast(massage: Any) {
    Toast.makeText(this, "$massage", Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(massage: Any) {
    Toast.makeText(requireContext(), "$massage", Toast.LENGTH_LONG).show()
}

fun View.visibilityInVisible() {
    this.visibility = View.INVISIBLE
}

fun View.visibilityGone() {
    this.visibility = View.GONE
}

fun View.visibilityVisible() {
    this.visibility = View.VISIBLE
}

fun View.disable() {
    isEnabled = false
}

fun View.enabled() {
    isEnabled = true
}

fun Fragment.convertTimeFormat(inputTime: String): String {
    val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    var outputTime = ""
    try {
        val date = inputFormat.parse(inputTime)
        val formattedTime = outputFormat.format(date)
        outputTime = formattedTime
    } catch (e: Exception) {
        // Handle parsing errors, if any
        outputTime = inputTime // add the original input if parsing fails
    }

    return outputTime
}

fun Fragment.getCurrentDate(): String {
    val currentDate = Date()
    val formatter = SimpleDateFormat("dd-MM-yyyy")
    return formatter.format(currentDate)
}

fun Fragment.getNextDate(day:Int): String {
    val calendar = Calendar.getInstance()
    calendar.time = Date() // Set the current date

    // Add one day to the current date
    calendar.add(Calendar.DAY_OF_MONTH, day)

    val formatter = SimpleDateFormat("dd-MM-yyyy")
    return formatter.format(calendar.time)
}

fun Context.getMillisDifference(inputTime: String, inputDate: String): Long {

    val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
    val currentDate = Calendar.getInstance()

    // Parse the input date and time
    val inputDateTime = dateFormat.parse("$inputDate $inputTime")

    return if (inputDateTime != null) {
        val inputCalendar = Calendar.getInstance()
        inputCalendar.time = inputDateTime

        // Calculate the difference in milliseconds
        val timeDifferenceInMillis = inputCalendar.timeInMillis - currentDate.timeInMillis

        timeDifferenceInMillis
    } else {
        0
    }
}

fun Context.timingsToArrayList(timings: TimingsEntity): ArrayList<String> {
    return arrayListOf(
        timings.Fajr,
        timings.Sunrise,
        timings.Dhuhr,
        timings.Asr,
        timings.Maghrib,
        timings.Isha,
    )
}
