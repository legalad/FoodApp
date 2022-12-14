package com.example.foodapp.model

import com.example.foodapp.data.Ingredient

data class IngredientUiState (
    var selected: Boolean,
    val ingredient: Ingredient
        )