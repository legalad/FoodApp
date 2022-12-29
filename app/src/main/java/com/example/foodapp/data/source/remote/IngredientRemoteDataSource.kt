package com.example.foodapp.data.source.remote

import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.Pantry
import com.example.foodapp.data.source.IngredientDataSource

object IngredientRemoteDataSource : IngredientDataSource {

    override suspend fun getIngredients(): List<Ingredient> {
        TODO("Not yet implemented")
    }

    override suspend fun addIngredientToPantry(pantry: Pantry) {
        TODO("Not yet implemented")
    }

    override suspend fun addPantryItemList(pantryList: List<Pantry>) {
        TODO("Not yet implemented")
    }
}