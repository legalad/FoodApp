package com.example.foodapp.ui.ingredients

import com.example.foodapp.data.Ingredient

data class IngredientsUiState (
    val ingredientList: List<Ingredient> = emptyList(),
    val selectedType: IngredientTypes = IngredientTypes.VEGETABLES,
    val selectedTypeItem: Int = 0,
    val input: String = "",
    val hideKeyboard: Boolean = false
        )