package com.andersonpimentel.bestrecipes.data.api

import com.andersonpimentel.bestrecipes.data.model.MealCategories
import com.andersonpimentel.bestrecipes.data.model.Meals
import com.andersonpimentel.bestrecipes.data.model.Recipes
import retrofit2.http.GET
import retrofit2.http.Query

interface GetApiData {

    @GET("categories.php")
    suspend fun getMealsCategories(): MealCategories

    @GET("filter.php")
    suspend fun getRecipesByCategory(
        @Query("c") category: String
    ): Meals

    @GET("lookup.php")
    suspend fun getRecipe(
        @Query("i") id: String
    ): Recipes

    companion object {

        var getApiService: GetApiData? = null

        fun getInstance(): GetApiData {

            if (getApiService == null) {
                val retrofit = ApiClientInstance.retrofitInstance
                getApiService = retrofit?.create(GetApiData::class.java)
            }
            return getApiService!!
        }
    }

}