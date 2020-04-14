package com.example.android.ce301charityapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.ce301charityapp.data.Animal
import com.example.android.ce301charityapp.R
import com.example.android.ce301charityapp.ui.utiilities.PreferenceHelper


class MainRecyclerAdapter(
    val context: Context,
    val animals: List<Animal>,
    private val itemListener: AnimalItemListener):
    RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>()
{
    override fun getItemCount() =animals.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutStyle = PreferenceHelper.getItemType(parent.context)
        val layoutId = if (layoutStyle == "grid"){
            R.layout.animal_grid_item
        } else {
            R.layout.animal_list_item
        }
        val view = inflater.inflate(layoutId, parent, false)
        return  ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = animals[position]
        with(holder){
            animalName.text = animal.animalName
            holder.itemView.setOnClickListener{
                itemListener.animalItemListener(animal)
            }
        }
    }


    inner class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){
        val animalName: TextView = itemView.findViewById(R.id.nameText)

    }
    interface AnimalItemListener {
        fun animalItemListener(animal: Animal)
    }


}