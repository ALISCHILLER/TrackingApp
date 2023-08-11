package com.msa.trackingapp.data.db

import androidx.room.Database


@Database(
    entities = [TrackingEntity::class],
    version = 1
)
abstract class TrackingDataBase {

    abstract fun getTrackingDao():TrackingDao
}