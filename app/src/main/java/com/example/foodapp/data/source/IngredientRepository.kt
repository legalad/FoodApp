package com.example.foodapp.data.source

import com.example.foodapp.data.Ingredient

interface IngredientRepository {

    suspend fun getIngredients(): Result<List<Ingredient>>

}
