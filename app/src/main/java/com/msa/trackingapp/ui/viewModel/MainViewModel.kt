package com.msa.trackingapp.ui.viewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msa.trackingapp.data.db.TrackingEntity
import com.msa.trackingapp.data.repository.TrackingRepository
import com.msa.trackingapp.util.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    val trackingRepository: TrackingRepository
):ViewModel(){

    private val runsSortedByDate = trackingRepository.getAllRunsSortedByDate()
    private val runsSortedByDistance = trackingRepository.getAllRunsSortedByDistance()
    private val runsSortedByTimeInMillis = trackingRepository.getAllRunsSortedByTimeInMillis()
    private val runsSortedByAvgSpeed = trackingRepository.getAllRunsSortedByAvgSpeed()
    private val runsSortedByCaloriesBurned = trackingRepository.getAllRunsSortedByCaloriesBurned()

    val runs = MediatorLiveData<List<TrackingEntity>>()

    var sortType = SortType.DATE

    /**
     * Posts the correct run list in the LiveData
     */
    init {
        runs.addSource(runsSortedByDate) { result ->
            Timber.d("RUNS SORTED BY DATE")
            if(sortType == SortType.DATE) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByDistance) { result ->
            if(sortType == SortType.DISTANCE) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByTimeInMillis) { result ->
            if(sortType == SortType.RUNNING_TIME) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByAvgSpeed) { result ->
            if(sortType == SortType.AVG_SPEED) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByCaloriesBurned) { result ->
            if(sortType == SortType.CALORIES_BURNED) {
                result?.let { runs.value = it }
            }
        }
    }

    fun sortRuns(sortType: SortType) = when(sortType) {
        SortType.DATE -> runsSortedByDate.value?.let { runs.value = it }
        SortType.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> runsSortedByTimeInMillis.value?.let { runs.value = it }
        SortType.AVG_SPEED -> runsSortedByAvgSpeed.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> runsSortedByCaloriesBurned.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }

    fun insertRun(run: TrackingEntity) = viewModelScope.launch {
        trackingRepository.insertRun(run)
    }

    fun deleteRun(run: TrackingEntity) = viewModelScope.launch {
        trackingRepository.deleteRun(run)
    }

}