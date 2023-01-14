package com.example.foodapp.ui

import androidx.compose.runtime.mutableStateListOf
import com.example.foodapp.data.IngredientEntity

data class FoodAppUiState (
    val ingredientEntityList: List<IngredientEntity> = mutableStateListOf()
)