package com.example.android.ce301charityapp.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.ce301charityapp.R
import com.example.android.ce301charityapp.data.Images


class GalleryRecyclerAdapter(val context: Context?, val images: List<Images>) : RecyclerView.Adapter<GalleryRecyclerAdapter.ImageViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.gallery_item, parent, false)
        )
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]

        with(holder){
            if (galleryImage != null) {
                if (context != null) {
                    Glide.with(context)
                        .load(image.ImageUrl)
                        .into(galleryImage)
                }
            }
        }
    }


    inner class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val galleryImage: ImageView? = itemView.findViewById(R.id.galleryImage)

    }
}

