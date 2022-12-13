package com.example.foodapp.ui

import com.example.foodapp.data.Ingredient

data class FoodAppUiState (
    val ingredientList: List<Ingredient> = emptyList()
)