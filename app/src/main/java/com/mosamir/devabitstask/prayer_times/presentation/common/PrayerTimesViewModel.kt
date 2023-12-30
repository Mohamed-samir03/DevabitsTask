package com.mosamir.devabitstask.prayer_times.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosamir.devabitstask.prayer_times.data.data_source.local.entity.TimingsEntity
import com.mosamir.devabitstask.prayer_times.domain.use_case.IDeleteLocalPrayerTimesUseCase
import com.mosamir.devabitstask.prayer_times.domain.use_case.IGetLocalPrayerTimesUseCase
import com.mosamir.devabitstask.prayer_times.domain.use_case.IGetPrayerTimesUseCase
import com.mosamir.devabitstask.util.IResult
import com.mosamir.devabitstask.util.NetworkState
import com.mosamir.devabitstask.util.getError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrayerTimesViewModel @Inject constructor(
    private val iGetPrayerTimesUseCase: IGetPrayerTimesUseCase,
    private val iGetLocalPrayerTimesUseCase: IGetLocalPrayerTimesUseCase,
    private val iDeleteLocalPrayerTimesUseCase: IDeleteLocalPrayerTimesUseCase
):ViewModel() {

    private val _getPrayerTimes: MutableStateFlow<NetworkState?> = MutableStateFlow(null)
    val getPrayerTimes: StateFlow<NetworkState?> =_getPrayerTimes

    private val _getLocalPrayerTimes: MutableStateFlow<IResult<List<TimingsEntity>>?> = MutableStateFlow(null)
    val getLocalPrayerTimes: StateFlow<IResult<List<TimingsEntity>>?> =_getLocalPrayerTimes

    fun getPrayerTimes(date: String,
                           latitude: Double,
                           longitude: Double,
                           method: Int
    ) {
        _getPrayerTimes.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = iGetPrayerTimesUseCase.getPrayerTimes(date, latitude, longitude, method)
                if (result.isSuccessful()){
                    _getPrayerTimes.value = NetworkState.getLoaded(result)
                }else{
                    _getPrayerTimes.value = NetworkState.getErrorMessage(result.getError().toString())
                }
            }catch (ex:Exception){
                ex.printStackTrace()
                _getPrayerTimes.value = NetworkState.getErrorMessage(ex)
            }
        }
    }

    fun getLocalePrayerTimes(){
        viewModelScope.launch {
            val result = iGetLocalPrayerTimesUseCase.getLocalPrayerTimes()
            _getLocalPrayerTimes.emit(result)
        }
    }

    fun deleteLocalePrayerTimes(){
        viewModelScope.launch {
            iDeleteLocalPrayerTimesUseCase.deletePrayerTimes()
        }
    }

}