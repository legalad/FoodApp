package com.example.foodapp.data.source

import com.example.foodapp.data.Ingredient

interface IngredientRepository {

    suspend fun getIngredients(): List<Ingredient>

    suspend fun addIngredient(ingredient: Ingredient)

}
