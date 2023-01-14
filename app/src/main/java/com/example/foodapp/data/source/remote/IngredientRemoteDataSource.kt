package com.example.foodapp.data.source.remote

import com.example.foodapp.data.source.IngredientDataSource
import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.PantryItem

object IngredientRemoteDataSource : IngredientDataSource {

    override suspend fun getIngredients(): List<Ingredient> {
        TODO("Not yet implemented")
    }

    override suspend fun addIngredientToPantry(pantryItem: PantryItem) {
        TODO("Not yet implemented")
    }

    override suspend fun addPantryItemList(pantryItemList: List<PantryItem>) {
        TODO("Not yet implemented")
    }
}