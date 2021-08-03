package com.andersonpimentel.bestrecipes.ui.recipe.fragment

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
import com.andersonpimentel.bestrecipes.R
import com.andersonpimentel.bestrecipes.data.api.GetApiData
import com.andersonpimentel.bestrecipes.data.model.Recipe
import com.andersonpimentel.bestrecipes.data.repository.Repository
import com.andersonpimentel.bestrecipes.databinding.RecipeDetailsFragmentBinding
import com.andersonpimentel.bestrecipes.databinding.RecipesFragmentBinding
import com.andersonpimentel.bestrecipes.ui.recipe.adapter.IngredientAdapter
import com.andersonpimentel.bestrecipes.ui.recipe.viewmodel.RecipeDetailsViewModel
import com.andersonpimentel.bestrecipes.ui.recipes.adapter.RecipesFragmentAdapter
import com.andersonpimentel.bestrecipes.ui.recipes.fragment.RecipesFragmentArgs
import com.andersonpimentel.bestrecipes.ui.recipes.viewmodel.RecipesViewModel
import com.bumptech.glide.Glide

class RecipeDetails : Fragment() {

    private val args: RecipeDetailsArgs by navArgs()
    private lateinit var binding: RecipeDetailsFragmentBinding
    private var getApiInstance = GetApiData.getInstance()
    private var repository = Repository(getApiInstance)
    private val ingredientAdapter by lazy { IngredientAdapter() }
    private lateinit var recipeDetailsViewModel: RecipeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecipeDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewModel()
        setupObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        binding.rvIngredients.adapter = ingredientAdapter
    }

    private fun setupViewModel(){
        recipeDetailsViewModel = ViewModelProvider(
            this,
            RecipeDetailsViewModel.RecipeDetailsViewModelFactory(repository = repository, args = args)
        ).get(RecipeDetailsViewModel::class.java)
    }

    private fun setupObservers(){
        recipeDetailsViewModel.recipeLiveData.observe(viewLifecycleOwner, Observer {
            val recipe = it.meals[0]
            setupView(recipe)
        })

        recipeDetailsViewModel.ingredientsLiveData.observe(viewLifecycleOwner, Observer {
            ingredientAdapter.setupRecyclerView(it)
        })
    }

    private fun setupView(recipe: Recipe){
        binding.tvRecipeTitle.text = recipe.strMeal

        Glide.with(binding.root)
            .load(recipe.strMealThumb)
            .into(binding.ivRecipe)

        binding.tvInstructions.text = recipe.strInstructions
    }

}