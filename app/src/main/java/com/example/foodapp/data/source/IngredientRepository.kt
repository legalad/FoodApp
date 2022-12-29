package com.example.foodapp.data.source

import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.Pantry

interface IngredientRepository {

    suspend fun getIngredients(): List<Ingredient>

    suspend fun addIngredientToPantry(pantry: Pantry)

    suspend fun addPantryItemList(pantryList: List<Pantry>)

}
