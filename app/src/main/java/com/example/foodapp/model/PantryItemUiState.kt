package com.example.foodapp.model

import com.example.foodapp.data.Pantry

data class PantryItemUiState (
    private val options: Array<Units> = Units.values(),
    val inputProductName: String,
    val selectedOptionText: String = options[0].name,
    val sliderPosition: Float = 0f,
    val isMenuExpanded: Boolean = false,
    val isPantryCollapsed: Boolean = true,
    val isPantryEdited: Boolean = false,
    val pantry: Pantry
        )