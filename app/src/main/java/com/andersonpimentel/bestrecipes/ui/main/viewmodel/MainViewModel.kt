package com.andersonpimentel.bestrecipes.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andersonpimentel.bestrecipes.data.model.MealCategories
import com.andersonpimentel.bestrecipes.data.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel constructor(private val repository: Repository) : ViewModel() {

    private lateinit var mealsCategories: MealCategories
    private val _mealsCategoriesLiveData = MutableLiveData<MealCategories>()
    private val _exception = MutableLiveData<Exception>()

    val mealsCategoriesLiveData: LiveData<MealCategories>
        get() = _mealsCategoriesLiveData

    val exception: LiveData<Exception>
        get() = _exception


    init {
        _mealsCategoriesLiveData
        getMealsCategories()
    }

    private fun getMealsCategories() {
        CoroutineScope(IO).launch {
            try {
                mealsCategories = repository.getMealsCategories()
                _mealsCategoriesLiveData.postValue(mealsCategories)
            } catch (e: Exception){
                Log.e("Error", e.message.toString())
                _exception.postValue(e)
            }
        }
    }

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

