package com.example.foodapp.data.utils

import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.Pantry
import com.example.foodapp.model.IngredientUiState
import com.example.foodapp.model.PantryItemUiState
import java.util.Calendar

class Mappers {
    companion object {

        fun fromIngredientToIngredientUiState(ingredient: Ingredient, selected: Boolean = false): IngredientUiState{
            return IngredientUiState(ingredient = ingredient, selected = selected)
        }

        fun fromIngredientUiStateToIngredient(ingredientUiState: IngredientUiState): Ingredient{
            return ingredientUiState.ingredient
        }

        fun fromIngredientToPantry(ingredient: Ingredient): Pantry {
            return Pantry(
                0,
                "Default " + ingredient.name,
                place_date = Calendar.getInstance().time,
                expire_date = Calendar.getInstance().time,
                quantity = 0.5f,
                unit = "KG",
                ingredient_id = ingredient.id
            )
        }

        fun fromPantryItemToPantryItemUiState(pantryItem: Pantry): PantryItemUiState {
            return PantryItemUiState(
                pantry = pantryItem,
                inputProductName = pantryItem.name,
                selectedOptionText = pantryItem.unit,
                sliderPosition = pantryItem.quantity
                )
        }
    }
}