package com.andersonpimentel.bestrecipes.ui.recipes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andersonpimentel.bestrecipes.data.model.Meals
import com.andersonpimentel.bestrecipes.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {

    private val _recipes = MutableLiveData<Meals>()
    val recipes = Transformations.map(_recipes){
        it
    }

    fun getRecipesByCategory(category: String) {
        CoroutineScope(IO).launch {
            _recipes.postValue(Repository.getRecipesByCategory(category))
        }
    }
}
