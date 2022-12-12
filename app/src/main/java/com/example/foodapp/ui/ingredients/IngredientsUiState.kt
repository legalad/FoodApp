package com.example.foodapp.ui.ingredients

data class IngredientsUiState (
    val selectedType: IngredientTypes = IngredientTypes.VEGETABLES,
    val selectedTypeItem: Int = 0,
    val input: String = "",
    val hideKeyboard: Boolean = false
        )