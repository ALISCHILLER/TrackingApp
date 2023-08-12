package com.msa.trackingapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [TrackingEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class TrackingDataBase :RoomDatabase() {

    abstract fun getTrackingDao():TrackingDao
}