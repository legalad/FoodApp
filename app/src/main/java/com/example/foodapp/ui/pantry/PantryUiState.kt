package com.example.foodapp.ui.pantry

import com.example.foodapp.data.Ingredient
import com.example.foodapp.model.PantryItemUiState

data class PantryUiState(
    val pantryItemsList: Map<PantryItemUiState, Ingredient> = emptyMap()
)
