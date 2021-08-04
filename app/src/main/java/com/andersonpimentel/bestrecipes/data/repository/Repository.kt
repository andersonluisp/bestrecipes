package com.andersonpimentel.bestrecipes.data.repository

import com.andersonpimentel.bestrecipes.data.api.GetApiData

object Repository {

    private val api = GetApiData.instance

    suspend fun getMealsCategories() = api.getMealsCategories()

    suspend fun getRecipesByCategory(category: String) = api.getRecipesByCategory(category)

    suspend fun getRecipe(id: String) = api.getRecipe(id)
}
