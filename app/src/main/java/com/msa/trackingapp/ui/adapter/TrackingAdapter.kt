package com.msa.trackingapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.msa.trackingapp.R
import com.msa.trackingapp.data.db.TrackingEntity
import com.msa.trackingapp.util.TrackingUtility
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TrackingAdapter: RecyclerView.Adapter<TrackingAdapter.TrackingViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<TrackingEntity>() {
        override fun areItemsTheSame(oldItem: TrackingEntity, newItem: TrackingEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TrackingEntity, newItem: TrackingEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackingViewHolder {
        return TrackingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_tracking,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TrackingViewHolder, position: Int) {
        val run = differ.currentList[position]
        val ivRunImage=holder.itemView.findViewById<ImageView>(R.id.ivRunImage)
        val tvDate=holder.itemView.findViewById<MaterialTextView>(R.id.tvDate)
        val tvAvgSpeed=holder.itemView.findViewById<MaterialTextView>(R.id.tvAvgSpeed)
        val tvDistance=holder.itemView.findViewById<MaterialTextView>(R.id.tvDistance)
        val tvTime=holder.itemView.findViewById<MaterialTextView>(R.id.tvTime)
        val tvCalories=holder.itemView.findViewById<MaterialTextView>(R.id.tvCalories)
        // set item data
        holder.itemView.apply {
            Glide.with(this).load(run.img).into(ivRunImage)

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
            tvTime.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)
            "${run.caloriesBurned}kcal".also {
                tvCalories.text = it
            }
        }
    }


    val differ = AsyncListDiffer(this, diffCallback)
    inner class TrackingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    fun submitList(list: List<TrackingEntity>) = differ.submitList(list)

}