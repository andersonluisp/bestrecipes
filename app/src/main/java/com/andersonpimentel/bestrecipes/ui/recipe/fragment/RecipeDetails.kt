package com.andersonpimentel.bestrecipes.ui.recipe.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.andersonpimentel.bestrecipes.app.load
import com.andersonpimentel.bestrecipes.data.model.Recipe
import com.andersonpimentel.bestrecipes.databinding.RecipeDetailsFragmentBinding
import com.andersonpimentel.bestrecipes.ui.recipe.adapter.IngredientAdapter
import com.andersonpimentel.bestrecipes.ui.recipe.viewmodel.RecipeDetailsViewModel

class RecipeDetails : Fragment() {

    private val args: RecipeDetailsArgs by navArgs()
    private lateinit var b: RecipeDetailsFragmentBinding
    private val vm by viewModels<RecipeDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = RecipeDetailsFragmentBinding.inflate(layoutInflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupRecyclerView()

        vm.loadRecipe(args.recipeId)
    }

    private fun setupRecyclerView() {
        b.rvIngredients.adapter = IngredientAdapter()
    }

    private fun setupObservers() {
        vm.recipe.observe(
            viewLifecycleOwner, {
                setupView(it.meals[0])
            }
        )

        vm.ingredients.observe(
            viewLifecycleOwner,
            {
                (b.rvIngredients.adapter as IngredientAdapter).setupRecyclerView(it)
            }
        )
    }

    private fun setupView(recipe: Recipe) {
        recipe.let {
            b.apply {
                tvRecipeTitle.text = it.strMeal
                ivRecipe.load(it.strMealThumb)
                tvInstructions.text = recipe.strInstructions
            }
        }
    }
}
