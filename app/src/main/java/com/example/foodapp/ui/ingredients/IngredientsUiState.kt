package com.example.foodapp.ui.ingredients

import androidx.compose.runtime.mutableStateListOf
import com.example.foodapp.model.IngredientUiState

data class IngredientsUiState (
    val ingredientList: List<IngredientUiState> = mutableStateListOf(),
    val selectedType: IngredientTypes = IngredientTypes.VEGETABLES,
    val selectedTypeItem: Int = 0,
    val input: String = "",
    val hideKeyboard: Boolean = false,
    val forceRecomposition: Boolean = false
        )