package com.andersonpimentel.bestrecipes.data.repository

import com.andersonpimentel.bestrecipes.data.api.GetApiData

class Repository constructor(private val getApiData: GetApiData) {

    suspend fun getMealsCategories() = getApiData.getMealsCategories()

    suspend fun getRecipesByCategory(category: String) = getApiData.getRecipesByCategory(category)

}