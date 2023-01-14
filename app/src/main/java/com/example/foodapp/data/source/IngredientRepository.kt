package com.example.foodapp.data.source

import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.PantryItem

interface IngredientRepository {

    suspend fun getIngredients(): List<Ingredient>

    suspend fun addIngredientToPantry(pantryItem: PantryItem)

    suspend fun addPantryItemList(pantryItemList: List<PantryItem>)

}
