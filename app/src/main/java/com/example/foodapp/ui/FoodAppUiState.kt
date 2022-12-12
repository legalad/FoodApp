package com.example.foodapp.ui

import com.example.foodapp.data.Datasource
import com.example.foodapp.model.Ingredient

data class FoodAppUiState (
    val ingredientList: List<Ingredient> = Datasource.ingredientList
)