package com.example.foodapp.ui.pantry

import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.model.PantryItemUiState

data class PantryUiState(
    val pantryItemsList: Map<PantryItemUiState, IngredientEntity> = emptyMap()
)
