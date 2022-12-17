package com.example.foodapp.ui

import androidx.compose.runtime.mutableStateListOf
import com.example.foodapp.data.Ingredient

data class FoodAppUiState (
    val ingredientList: List<Ingredient> = mutableStateListOf()
)