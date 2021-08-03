package com.andersonpimentel.bestrecipes.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andersonpimentel.bestrecipes.data.model.MealCategories
import com.andersonpimentel.bestrecipes.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel constructor(private val repository: Repository) : ViewModel() {

    private lateinit var mealsCategories: MealCategories
    val mealsCategoriesLiveData = MutableLiveData<MealCategories>()

    init {
        mealsCategoriesLiveData
        getMealsCategories()
    }

    fun getMealsCategories(){

        CoroutineScope(IO).launch {
            mealsCategories = repository.getMealsCategories()
            mealsCategoriesLiveData.postValue(mealsCategories)
        }
    }

    // TODO: Implement the ViewModel




    class MainViewModelFactory constructor(private val repository: Repository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                MainViewModel(this.repository) as T
            } else {
                throw IllegalAccessException("ViewModel Not Found")
            }
        }

    }
}

