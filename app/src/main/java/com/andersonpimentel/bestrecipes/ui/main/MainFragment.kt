package com.andersonpimentel.bestrecipes.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.andersonpimentel.bestrecipes.R
import com.andersonpimentel.bestrecipes.data.api.GetApiData
import com.andersonpimentel.bestrecipes.data.repository.Repository
import com.andersonpimentel.bestrecipes.databinding.MainActivityBinding
import com.andersonpimentel.bestrecipes.databinding.MainFragmentBinding
import com.andersonpimentel.bestrecipes.ui.adapter.MealCategoriesAdapter

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

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvMealsCategories.layoutManager = linearLayoutManager
        binding.rvMealsCategories.adapter = adapter
    }

    private fun setupViewModel(){
        mainViewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(repository = repository)
        ).get(MainViewModel::class.java)
    }

    private fun setupObservers(){
        mainViewModel.mealsCategoriesLiveData.observe(viewLifecycleOwner, Observer {
            it.categories.forEach{ category ->
                Log.e("Teste", category.strCategory)
            }
            adapter.setupRecyclerView(it.categories)
        })
    }

}