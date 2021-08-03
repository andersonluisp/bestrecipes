package com.andersonpimentel.bestrecipes.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andersonpimentel.bestrecipes.data.api.GetApiData
import com.andersonpimentel.bestrecipes.data.repository.Repository
import com.andersonpimentel.bestrecipes.databinding.MainFragmentBinding
import com.andersonpimentel.bestrecipes.ui.main.adapter.MealCategoriesAdapter
import com.andersonpimentel.bestrecipes.ui.main.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var mainViewModel: MainViewModel
    private var getApiInstance = GetApiData.getInstance()
    private var repository = Repository(getApiInstance)
    private val adapter by lazy { MealCategoriesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModel()
        setupObservers()
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvMealsCategories.layoutManager = linearLayoutManager
        binding.rvMealsCategories.adapter = adapter
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(repository = repository)
        ).get(MainViewModel::class.java)
    }

    private fun setupObservers() {
        mainViewModel.mealsCategoriesLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setupRecyclerView(it.categories)
        })
    }

}