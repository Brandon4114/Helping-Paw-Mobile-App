package com.example.android.ce301charityapp.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.ce301charityapp.Data.Animal

class MainRecyclerAdapter(
    val context: Context,
    val animals: List<Animal>,
    val itemListener: AnimalItemListener):
    RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    inner class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){

    }
    interface AnimalItemListener {
        fun AnimalItemListener(animal: Animal)
    }


}