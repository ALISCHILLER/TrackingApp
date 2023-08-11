package com.msa.trackingapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TrackingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: TrackingEntity)

    @Delete
    suspend fun deleteRun(run: TrackingEntity)

    @Query("SELECT * FROM tracking ORDER BY timestamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<TrackingEntity>>

    @Query("SELECT * FROM tracking ORDER BY timeInMillis DESC")
    fun getAllRunsSortedByTimeInMillis(): LiveData<List<TrackingEntity>>

    @Query("SELECT * FROM tracking ORDER BY caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<TrackingEntity>>

    @Query("SELECT * FROM tracking ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<TrackingEntity>>

    @Query("SELECT * FROM tracking ORDER BY avgSpeedInKMH DESC")
    fun getAllRunsSortedByAvgSpeed(): LiveData<List<TrackingEntity>>

    @Query("SELECT SUM(timeInMillis) FROM tracking")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(distanceInMeters) FROM tracking")
    fun getTotalDistance(): LiveData<Int>

    @Query("SELECT AVG(avgSpeedInKMH) FROM tracking")
    fun getTotalAvgSpeed(): LiveData<Float>

    @Query("SELECT SUM(caloriesBurned) FROM tracking")
    fun getTotalCaloriesBurned(): LiveData<Long>



}