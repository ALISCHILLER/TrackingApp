package com.msa.trackingapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.msa.trackingapp.R
import com.msa.trackingapp.data.db.TrackingEntity

import java.text.SimpleDateFormat
import java.util.*

/**
 * Pop-up window, when we click on a bar in the bar chart
 */
@SuppressLint("ViewConstructor")
class CustomMarkerView(
    val runs: List<TrackingEntity>,
    c: Context,
    layoutId: Int
) : MarkerView(c, layoutId) {

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if(e == null) {
            return
        }
        val tvDate= findViewById<TextView>(R.id.tvDate)
        val tvAvgSpeed= findViewById<TextView>(R.id.tvAvgSpeed)
        val tvDistance= findViewById<TextView>(R.id.tvDistance)
        val tvDuration= findViewById<TextView>(R.id.tvDuration)
        val tvCaloriesBurned= findViewById<TextView>(R.id.tvCaloriesBurned)

        val curRunId = e.x.toInt()
        val run = runs[curRunId]
        val calendar = Calendar.getInstance().apply {
            timeInMillis = run.timestamp
        }
        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        tvDate.text = dateFormat.format(calendar.time)

        "${run.avgSpeedInKMH}km/h".also {
            tvAvgSpeed.text = it
        }
        "${run.distanceInMeters / 1000f}km".also {
            tvDistance.text = it
        }
        tvDuration.text =
            TrackingUtility.getFormattedStopWatchTime(
                run.timeInMillis
            )
        "${run.caloriesBurned}kcal".also {
            tvCaloriesBurned.text = it
        }
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-width / 2f, -height.toFloat())
    }
}