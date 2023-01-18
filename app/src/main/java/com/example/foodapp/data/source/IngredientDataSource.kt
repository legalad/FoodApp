package com.example.foodapp.data.source

import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.PantryItem
import com.example.foodapp.data.Result

interface IngredientDataSource {

    suspend fun getIngredients(): Result<List<Ingredient>>

    suspend fun addIngredientToPantry(pantryItem: PantryItem): Result<PantryItem>

    suspend fun addPantryItemList(pantryItemList: List<PantryItem>): Result<List<PantryItem>>

}