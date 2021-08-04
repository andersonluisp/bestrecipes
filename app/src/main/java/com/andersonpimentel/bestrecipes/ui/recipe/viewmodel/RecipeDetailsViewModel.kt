package com.andersonpimentel.bestrecipes.ui.recipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andersonpimentel.bestrecipes.data.model.Ingredient
import com.andersonpimentel.bestrecipes.data.model.Recipes
import com.andersonpimentel.bestrecipes.data.repository.Repository
import com.andersonpimentel.bestrecipes.ui.recipe.fragment.RecipeDetailsArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RecipeDetailsViewModel : ViewModel() {

    private var _recipe = MutableLiveData<Recipes>()
    var recipe = Transformations.map(_recipe){
        it
    }

    private var _ingredients = MutableLiveData<ArrayList<Ingredient>>()
    var ingredients = Transformations.map(_ingredients){
        it
    }

    fun loadRecipe(recipeId: String) {
        CoroutineScope(IO).launch {
            Repository.getRecipe(recipeId).let {
                _recipe.postValue(it)
                _ingredients.postValue(it.meals[0].filterBlankIngredient())
            }
        }
    }
}