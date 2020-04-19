package com.example.android.ce301charityapp.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.ce301charityapp.R
import com.example.android.ce301charityapp.data.ProgressPoints
import kotlinx.android.synthetic.main.progress_item.view.*

class DetailRecyclerAdapter(val points: List<ProgressPoints>) : RecyclerView.Adapter<DetailRecyclerAdapter.ProgressViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        return ProgressViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.progress_item, parent, false)
        )
    }

    override fun getItemCount() = points.size

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        val point = points[position]

        holder.view.point_number.text = point.animalPoint.toString()
        holder.view.point_description.text = point.progressDescription
    }


    class ProgressViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}