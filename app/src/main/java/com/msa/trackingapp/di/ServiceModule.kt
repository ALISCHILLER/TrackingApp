package com.msa.trackingapp.di

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.msa.trackingapp.R
import com.msa.trackingapp.ui.activity.MainActivity
import com.msa.trackingapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped


@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    // We need this client to access user location
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context) = FusedLocationProviderClient(context)

    @ServiceScoped
    @Provides
    // This is the activity where our service belongs to
    fun providePendingIntent(@ApplicationContext context: Context) = PendingIntent.getActivity(
        context, 0,
        Intent(context, MainActivity::class.java).also {
            it.action = Constants.ACTION_SHOW_TRACKING_ACTIVITY
        }, PendingIntent.FLAG_UPDATE_CURRENT
    )

    @ServiceScoped
    @Provides
    // Building the actual notification that will be displayed
    fun provideBaseNotificationBuilder(@ApplicationContext context: Context, pendingIntent: PendingIntent) = NotificationCompat.Builder(context, Constants.NOTIFICATION_CHANNEL_ID)
        .setAutoCancel(false)
        .setOngoing(true)
        .setSmallIcon(R.drawable.ic_bike)
        .setContentTitle("حرکت کن MSA")
        .setContentText("00:00:00")
        .setContentIntent(pendingIntent)

}