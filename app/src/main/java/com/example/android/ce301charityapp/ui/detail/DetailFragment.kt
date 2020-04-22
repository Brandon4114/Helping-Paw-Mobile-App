package com.example.android.ce301charityapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.ce301charityapp.LOG_TAG
import com.example.android.ce301charityapp.R
import com.example.android.ce301charityapp.data.Images
import com.example.android.ce301charityapp.data.ProgressPoints
import com.example.android.ce301charityapp.databinding.FragmentDetailBinding
import com.example.android.ce301charityapp.ui.shared.SharedViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.profile_image
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    private lateinit var navController: NavController

    companion object{
        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
        val binding = FragmentDetailBinding.inflate(
            inflater, container, false
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val progress = getProgressItems(viewModel.progressData, viewModel.selectedAnimal.value!!.id)
        Log.i(LOG_TAG, "$progress")

        val images = getImages(viewModel.imageData,viewModel.selectedAnimal.value!!.id)
        Log.i(LOG_TAG,"$images")
        recyclerViewPoints.layoutManager = LinearLayoutManager(activity)
        recyclerViewPoints.adapter = progress?.let { DetailRecyclerAdapter(it) }

        recyclerViewGallery.layoutManager = LinearLayoutManager(activity)
        recyclerViewGallery.adapter = images?.let {GalleryRecyclerAdapter(context,it)}

        with(view){
                Glide.with(context)
                    .load(viewModel.selectedAnimal.value!!.ImageUrl)
                    .into(view.findViewById(R.id.profile_image))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getProgressItems(list: MutableLiveData<List<ProgressPoints>>, id: Int): List<ProgressPoints>? {
        val oldlist = list.value
        return oldlist?.filter { it.animalID == id }
    }

    private fun getImages(list: MutableLiveData<List<Images>>, id: Int): List<Images>? {
        val oldlist = list.value
        return oldlist?.filter { it.animalID == id }
    }

}