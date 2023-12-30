package com.mosamir.devabitstask.prayer_times.presentation.fragment

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import com.mosamir.devabitstask.databinding.FragmentPrayerTimesBinding
import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity
import com.mosamir.devabitstask.prayer_times.domain.model.PrayerTimes
import com.mosamir.devabitstask.prayer_times.presentation.common.PrayerTimesViewModel
import com.mosamir.devabitstask.util.Constants.Companion.PERMISSIONS_REQUEST_LOCATION
import com.mosamir.devabitstask.util.Constants.Companion.PRAYER_TIMES_METHOD
import com.mosamir.devabitstask.util.Constants.Companion.REGISTER_PRAYERS
import com.mosamir.devabitstask.util.Constants.Companion.prayerTimes
import com.mosamir.devabitstask.util.IResult
import com.mosamir.devabitstask.util.NetworkState
import com.mosamir.devabitstask.util.PrayerTimesWorker
import com.mosamir.devabitstask.util.convertTimeFormat
import com.mosamir.devabitstask.util.getCurrentDate
import com.mosamir.devabitstask.util.getNextDate
import com.mosamir.devabitstask.util.showToast
import com.mosamir.devabitstask.util.visibilityGone
import com.mosamir.devabitstask.util.visibilityVisible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class PrayerTimesFragment : Fragment() {

    private var _binding: FragmentPrayerTimesBinding? = null
    private val binding get() = _binding!!
    private val prayerTimesViewModel by viewModels<PrayerTimesViewModel>()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var userLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPrayerTimesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        if (checkLocationPermission()) {
            getLastLocation()
        } else {
            requestLocationPermission()
        }

        prayerTimesViewModel.getLocalePrayerTimes()
        observer()
        onClick()

    }

    private fun onClick(){
        binding.btnUpdatePrayerTimes.setOnClickListener {
            userLocation?.let {loc ->
                prayerTimesViewModel.deleteLocalePrayerTimes()
                for (i in 0..6){
                    prayerTimesViewModel.getPrayerTimes(getNextDate(i),loc.latitude,loc.longitude,PRAYER_TIMES_METHOD)
                }
            }
        }
    }

    private fun observer(){
        lifecycleScope.launch {
            prayerTimesViewModel.getPrayerTimes.collect { networkState ->
                when (networkState?.status) {
                    NetworkState.Status.SUCCESS -> {
                        binding.prayerTimesProgressBar.visibilityGone()
                        val data = networkState.data as IResult<PrayerTimes>
                        prayerTimesViewModel.getLocalePrayerTimes()
                    }

                    NetworkState.Status.FAILED -> {
                        binding.prayerTimesProgressBar.visibilityGone()
                        showToast(networkState.msg.toString())
                    }

                    NetworkState.Status.RUNNING -> {
                        binding.prayerTimesProgressBar.visibilityVisible()
                    }

                    else -> {}
                }
            }
        }
        lifecycleScope.launch {
            prayerTimesViewModel.getLocalPrayerTimes.collect{ result ->
                when (result){
                    is IResult.Success ->{
                        updateUI(result.data)
                    }
                    is IResult.Fail ->{
                        showToast("قم بتحديث مواقيت الصلاه")
                    }
                    else ->{}
                }
            }
        }
    }

    private fun updateUI(times:List<TimingsEntity>) {
        prayerTimes = times
        WorkManager.getInstance(requireContext()).cancelAllWork()
        val workRequest = PeriodicWorkRequest.Builder(PrayerTimesWorker::class.java, 7, TimeUnit.DAYS)
            .addTag(REGISTER_PRAYERS)
            .build()
        WorkManager.getInstance(requireContext()).enqueue(workRequest)
        val time = times.find { it.date == getCurrentDate() }
        if (time != null){
            binding.apply {
                tvFajrTime.text = convertTimeFormat(time.Fajr)
                tvSunriseTime.text = convertTimeFormat(time.Sunrise)
                tvDhuhrTime.text = convertTimeFormat(time.Dhuhr)
                tvAsrTime.text = convertTimeFormat(time.Asr)
                tvMaghribTime.text = convertTimeFormat(time.Maghrib)
                tvIshaTime.text = convertTimeFormat(time.Isha)
            }
        }
    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener(requireActivity(), OnSuccessListener<Location> { location ->
                if (location != null) {
                    userLocation = location
                }
            })
    }

    private fun checkLocationPermission(): Boolean {
        val permissionCheck = ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        return permissionCheck == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSIONS_REQUEST_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                getLastLocation()
            } else {
                // Permission denied
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}