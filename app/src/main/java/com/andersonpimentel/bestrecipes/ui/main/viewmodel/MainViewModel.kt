package com.andersonpimentel.bestrecipes.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andersonpimentel.bestrecipes.app.ImportDataException
import com.andersonpimentel.bestrecipes.data.model.MealCategories
import com.andersonpimentel.bestrecipes.data.repository.Repository
import com.andersonpimentel.bestrecipes.ui.recipe.viewmodel.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {
    private lateinit var mealsCategories: MealCategories
    private val _mealsCategoriesLiveData = MutableLiveData<MealCategories>()

    val mealsCategoriesLiveData = Transformations.map(_mealsCategoriesLiveData) {
        it.categories.mapTo(arrayListOf()) { data ->
            Category(
                id = data.idCategory,
                title = data.strCategory,
                description = data.strCategoryDescription,
                thumbUrl = data.strCategoryThumb
            )
        }
    }

    val exception = MutableLiveData<Exception>()

    init {
        _mealsCategoriesLiveData
        getMealsCategories()
    }

    private fun getMealsCategories() {
        CoroutineScope(IO).launch {
            try {
                _mealsCategoriesLiveData.postValue(Repository.getMealsCategories())
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                exception.postValue(ImportDataException())
            }
        }
    }
}
