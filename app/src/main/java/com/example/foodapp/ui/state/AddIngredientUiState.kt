package com.example.foodapp.ui.state

import com.example.foodapp.ui.screens.IngredientTypes

data class AddIngredientUiState (
    val selectedType: IngredientTypes = IngredientTypes.VEGETABLES,
    val selectedTypeItem: Int = 0,
    val input: String = "",
    val hideKeyboard: Boolean = false
        )