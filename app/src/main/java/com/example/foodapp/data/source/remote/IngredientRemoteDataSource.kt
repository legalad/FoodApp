package com.example.foodapp.data.source.remote

import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.source.IngredientDataSource

object IngredientRemoteDataSource : IngredientDataSource {

    override suspend fun getIngredients(): Result<List<Ingredient>> {
        TODO("Not yet implemented")
    }

}