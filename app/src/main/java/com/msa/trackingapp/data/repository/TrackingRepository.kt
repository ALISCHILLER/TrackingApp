package com.msa.trackingapp.data.repository

import com.msa.trackingapp.data.db.TrackingDao
import com.msa.trackingapp.data.db.TrackingEntity
import javax.inject.Inject

class TrackingRepository @Inject constructor(
    val runDao: TrackingDao
) {
    suspend fun insertRun(run: TrackingEntity) = runDao.insertRun(run)

    suspend fun deleteRun(run: TrackingEntity) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate()

    fun getAllRunsSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeInMillis()

    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance()

    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned()

    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed()

    fun getTotalDistance() = runDao.getTotalDistance()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()
}