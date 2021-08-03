package com.andersonpimentel.bestrecipes.ui.recipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andersonpimentel.bestrecipes.data.model.Ingredient
import com.andersonpimentel.bestrecipes.data.model.Recipes
import com.andersonpimentel.bestrecipes.data.repository.Repository
import com.andersonpimentel.bestrecipes.ui.recipe.fragment.RecipeDetailsArgs
import com.andersonpimentel.bestrecipes.ui.recipes.fragment.RecipesFragmentArgs
import com.andersonpimentel.bestrecipes.ui.recipes.viewmodel.RecipesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RecipeDetailsViewModel constructor(
    private val repository: Repository,
    private val args: RecipeDetailsArgs
) : ViewModel() {

    private lateinit var recipes: Recipes
    var recipeLiveData = MutableLiveData<Recipes>()
    var ingredientsLiveData = MutableLiveData<ArrayList<Ingredient>>()

    init {
        recipeLiveData
        getRecipe()
        ingredientsLiveData
    }

    private fun getRecipe(){
        CoroutineScope(IO).launch {
            recipes = repository.getRecipe(args.recipeId)
            recipeLiveData.postValue(recipes)
            ingredientsLiveData.postValue(recipes.meals[0].filterBlankIngredient())
        }
    }

    class RecipeDetailsViewModelFactory constructor(
        private val repository: Repository,
        private val args: RecipeDetailsArgs
    ) :
        ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RecipeDetailsViewModel(this.repository, this.args) as T
        }
    }

}