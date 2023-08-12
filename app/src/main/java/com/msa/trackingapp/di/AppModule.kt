package com.msa.trackingapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.msa.trackingapp.data.db.TrackingDao
import com.msa.trackingapp.data.db.TrackingDataBase
import com.msa.trackingapp.util.Constants.Companion.DATABASE_NAME
import com.msa.trackingapp.util.Constants.Companion.KEY_FIRST_TIME_TOGGLE
import com.msa.trackingapp.util.Constants.Companion.KEY_NAME
import com.msa.trackingapp.util.Constants.Companion.KEY_WEIGHT
import com.msa.trackingapp.util.Constants.Companion.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideAppDb(app: Application): TrackingDataBase {
        return Room.databaseBuilder(app, TrackingDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRunDao(db: TrackingDataBase): TrackingDao {
        return db.getTrackingDao()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(app: Application) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPreferences: SharedPreferences) =
        sharedPreferences.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPreferences: SharedPreferences) =
        sharedPreferences.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPreferences: SharedPreferences) = sharedPreferences.getBoolean(
        KEY_FIRST_TIME_TOGGLE, true
    )

}