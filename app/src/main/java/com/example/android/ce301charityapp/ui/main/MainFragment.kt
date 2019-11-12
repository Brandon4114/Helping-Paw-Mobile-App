package com.example.android.ce301charityapp.ui.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.android.ce301charityapp.Data.Animal
import com.example.android.ce301charityapp.R
import com.example.android.ce301charityapp.ui.utiilities.PreferenceHelper
import com.example.androiddata.ui.shared.SharedViewModel

class MainFragment : Fragment(),
    MainRecyclerAdapter.AnimalItemListener{

    private lateinit var viewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var navController: NavController
    private lateinit var adapter: MainRecyclerAdapter


    override fun AnimalItemListener(animal: Animal) {
        viewModel.selectedAnimal.value = animal
        navController.navigate(R.id.action_nav_detail)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        val layoutStyle = PreferenceHelper.getItemType(requireContext())
        recyclerView.layoutManager =
            if(layoutStyle == "grid"){
                GridLayoutManager(requireContext(), 2)
            } else {
                LinearLayoutManager(requireContext())
            }

        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        swipeLayout = view.findViewById(R.id.swipeLayout)

        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
        viewModel.animalData.observe(this, Observer
        {

            adapter = MainRecyclerAdapter(requireContext(), it, this)
            recyclerView.adapter = adapter
            swipeLayout.isRefreshing = false
        })

        viewModel.activityTitle.observe(this, Observer {
            requireActivity().title = it
        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_view_grid -> {
                PreferenceHelper.setItemType(requireContext(), "grid")
                recyclerView.layoutManager =
                    GridLayoutManager(requireContext(), 2)
                recyclerView.adapter = adapter
            }
            R.id.action_view_list -> {
                PreferenceHelper.setItemType(requireContext(), "list")
                recyclerView.layoutManager =
                    LinearLayoutManager(requireContext())
                recyclerView.adapter = adapter
            }
            R.id.action_settings -> {
                navController.navigate(R.id.settings)
            }
        }
        return true
    }

}