package com.msa.trackingapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.msa.trackingapp.data.repository.TrackingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    val trackingRepository: TrackingRepository
):ViewModel(){

    var totalDistance = trackingRepository.getTotalDistance()
    var totalTimeInMillis = trackingRepository.getTotalTimeInMillis()
    var totalAvgSpeed = trackingRepository.getTotalAvgSpeed()
    var totalCaloriesBurned = trackingRepository.getTotalCaloriesBurned()

    var runsSortedByDate = trackingRepository.getAllRunsSortedByDate()

}