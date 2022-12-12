package com.example.foodapp.data.source

import com.example.foodapp.data.Ingredient

interface IngredientDataSource {

    suspend fun getIngredients(): Result<List<Ingredient>>

}