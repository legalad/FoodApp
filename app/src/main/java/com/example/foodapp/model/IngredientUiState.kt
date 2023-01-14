package com.example.foodapp.model

import com.example.foodapp.data.IngredientEntity

data class IngredientUiState (
    val selected: Boolean,
    val ingredientEntity: IngredientEntity
        )