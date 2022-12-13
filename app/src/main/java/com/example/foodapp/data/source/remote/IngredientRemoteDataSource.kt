package com.example.foodapp.data.source.remote

import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.source.IngredientDataSource

object IngredientRemoteDataSource : IngredientDataSource {

    override suspend fun getIngredients(): List<Ingredient> {
        TODO("Not yet implemented")
    }

    override suspend fun addIngredient(ingredient: Ingredient) {
        TODO("Not yet implemented")
    }

}