package com.example.foodapp.data.utils

import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.PantryEntity
import com.example.foodapp.model.IngredientUiState
import com.example.foodapp.model.PantryItem
import com.example.foodapp.model.PantryItemUiState
import java.util.Calendar

class Mappers {
    fun Map<PantryEntity, IngredientEntity>.toPantryItemList() : List<PantryItem> {
        val list: MutableList<PantryItem> = mutableListOf()
        this.forEach { (pantryEntity, ingredientEntity) ->
            list.add(PantryItem(
                item_id = pantryEntity.item_id,
                item_name = pantryEntity.item_name,
                bar_code = pantryEntity.bar_code,
                place_date = pantryEntity.place_date,
                expire_date = pantryEntity.expire_date,
                quantity = pantryEntity.quantity,
                unit = pantryEntity.unit,
                ingredientEntity = ingredientEntity
                ))
        }
        return list
    }

    companion object {

        fun fromIngredientToIngredientUiState(ingredientEntity: IngredientEntity, selected: Boolean = false): IngredientUiState{
            return IngredientUiState(ingredientEntity = ingredientEntity, selected = selected)
        }

        fun fromIngredientUiStateToIngredient(ingredientUiState: IngredientUiState): IngredientEntity{
            return ingredientUiState.ingredientEntity
        }

        fun fromIngredientToPantry(ingredientEntity: IngredientEntity): PantryEntity {
            return PantryEntity(
                0,
                "Default " + ingredientEntity.ingredient_name,
                place_date = Calendar.getInstance().time,
                expire_date = Calendar.getInstance().time,
                quantity = 0.5f,
                unit = "KG",
                ingredient_id = ingredientEntity.ingredient_id
            )
        }

        fun fromPantryItemToPantryItemUiState(pantryEntityItem: PantryEntity): PantryItemUiState {
            return PantryItemUiState(
                pantryEntity = pantryEntityItem,
                inputProductName = pantryEntityItem.item_name,
                selectedOptionText = pantryEntityItem.unit,
                sliderPosition = pantryEntityItem.quantity
                )
        }
    }
}