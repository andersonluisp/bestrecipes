package com.andersonpimentel.bestrecipes.ui.recipes.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.andersonpimentel.bestrecipes.R
import com.andersonpimentel.bestrecipes.data.api.GetApiData
import com.andersonpimentel.bestrecipes.data.repository.Repository
import com.andersonpimentel.bestrecipes.databinding.RecipesFragmentBinding
import com.andersonpimentel.bestrecipes.ui.main.viewmodel.MainViewModel
import com.andersonpimentel.bestrecipes.ui.recipes.adapter.RecipesFragmentAdapter
import com.andersonpimentel.bestrecipes.ui.recipes.viewmodel.RecipesViewModel

class RecipesFragment : Fragment() {

    private val args: RecipesFragmentArgs by navArgs()
    private lateinit var binding: RecipesFragmentBinding
    private lateinit var recipesViewModel: RecipesViewModel
    private var getApiInstance = GetApiData.getInstance()
    private var repository = Repository(getApiInstance)
    private val adapter by lazy { RecipesFragmentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecipesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        setupViewModel()
        setupObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvRecipes.addItemDecoration(decoration)
        binding.rvRecipes.adapter = adapter
    }

    private fun setupViewModel(){
        recipesViewModel = ViewModelProvider(
            this,
            RecipesViewModel.RecipesViewModelFactory(repository = repository, args = args)
        ).get(RecipesViewModel::class.java)
    }

    private fun setupObservers(){
        recipesViewModel.recipesLiveData.observe(viewLifecycleOwner, Observer {
            it.meals.forEach{ meal ->
                Log.e("Teste", meal.strMeal)
            }
            adapter.setupRecyclerView(it.meals)
        })
    }
}