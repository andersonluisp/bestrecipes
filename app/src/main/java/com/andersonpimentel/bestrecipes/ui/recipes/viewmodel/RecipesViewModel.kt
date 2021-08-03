package com.andersonpimentel.bestrecipes.ui.recipes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andersonpimentel.bestrecipes.data.model.Meals
import com.andersonpimentel.bestrecipes.data.repository.Repository
import com.andersonpimentel.bestrecipes.ui.recipes.fragment.RecipesFragmentArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RecipesViewModel constructor(
    private val repository: Repository,
    private val args: RecipesFragmentArgs
) : ViewModel() {

    private lateinit var recipes: Meals
    val recipesLiveData = MutableLiveData<Meals>()

    init {
        recipesLiveData
        getRecipesByCategory(args.categoryRecipes)
    }

    fun getRecipesByCategory(category: String) {
        CoroutineScope(IO).launch {
            recipes = repository.getRecipesByCategory(category)
            recipesLiveData.postValue(recipes)
        }
    }

    class RecipesViewModelFactory constructor(
        private val repository: Repository,
        private val args: RecipesFragmentArgs
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RecipesViewModel(this.repository, this.args) as T
        }
    }
}