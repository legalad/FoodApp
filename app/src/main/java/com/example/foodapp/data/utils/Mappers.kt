package com.example.foodapp.data.utils

import com.example.foodapp.data.Ingredient
import com.example.foodapp.model.IngredientUiState

class Mappers {
    companion object {

        fun fromIngredientToIngredientUiState(ingredient: Ingredient, selected: Boolean = false): IngredientUiState{
            return IngredientUiState(ingredient = ingredient, selected = selected)
        }

        fun fromIngredientUiStateToIngredient(ingredientUiState: IngredientUiState): Ingredient{
            return ingredientUiState.ingredient
        }
    }
}