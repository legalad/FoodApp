package com.example.foodapp.ui.ingredients

import androidx.compose.runtime.mutableStateListOf
import com.example.foodapp.model.IngredientUiState
import com.example.foodapp.model.PantryItemUiState

sealed interface IngredientsScreenUiState {
    data class Success(
        val ingredientList: List<IngredientUiState> = mutableStateListOf<IngredientUiState>(),
        val pantryItemList: List<PantryItemUiState> = mutableStateListOf<PantryItemUiState>(),
        val selectedType: IngredientTypes = IngredientTypes.VEGETABLES,
        val selectedTypeItem: Int = 0,
        val input: String = "",
        val hideKeyboard: Boolean = false,
        val addingScreen: Boolean = false,
        val forceRecomposition: Boolean = false
    ) : IngredientsScreenUiState
    object Loading : IngredientsScreenUiState
    data class Error(val s: String) : IngredientsScreenUiState
}

data class IngredientsUiState (
    val ingredientList: List<IngredientUiState> = mutableStateListOf<IngredientUiState>(),
    val pantryItemList: List<PantryItemUiState> = mutableStateListOf<PantryItemUiState>(),
    val selectedType: IngredientTypes = IngredientTypes.VEGETABLES,
    val selectedTypeItem: Int = 0,
    val input: String = "",
    val hideKeyboard: Boolean = false,
    val addingScreen: Boolean = false,
    val forceRecomposition: Boolean = false
        )