package com.example.foodapp.ui.pantry

import com.example.foodapp.model.PantryItemUiState

data class PantryUiState(
    val pantryItemsList: List<PantryItemUiState> = emptyList()
)
