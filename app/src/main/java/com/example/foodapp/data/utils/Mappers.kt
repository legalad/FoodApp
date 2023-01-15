package com.example.foodapp.data.utils

import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.PantryEntity
import com.example.foodapp.data.source.remote.RemoteIngredient
import com.example.foodapp.data.source.remote.RemotePantryItem
import com.example.foodapp.data.source.remote.RemotePantryItemRequest
import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.IngredientUiState
import com.example.foodapp.model.PantryItem
import com.example.foodapp.model.PantryItemUiState
import java.util.Calendar

fun Map<PantryEntity, IngredientEntity>.toPantryItemList() : List<PantryItem> {
    val list: MutableList<PantryItem> = mutableListOf()
    this.forEach { (pantryEntity, ingredientEntity) ->
        list.add(PantryItem(
            id = pantryEntity.item_id,
            name = pantryEntity.item_name,
            barCode = pantryEntity.bar_code,
            placeDate = pantryEntity.place_date,
            expireDate = pantryEntity.expire_date,
            quantity = pantryEntity.quantity,
            unit = pantryEntity.unit,
            ingredient = ingredientEntity.toIngredient()
        ))
    }
    return list
}

fun  List<IngredientEntity>.toIngredientList() : List<Ingredient> {
    return map {
        it.toIngredient()
    }
}

fun IngredientEntity.toIngredient(): Ingredient {
    return Ingredient(
        ingredient_id = ingredient_id,
        ingredient_name = ingredient_name,
        scientific_name = scientific_name,
        group = group,
        subGroup = subGroup
    )
}

fun Ingredient.toIngredientEntity(): IngredientEntity {
    return IngredientEntity(
        ingredient_id = ingredient_id,
        ingredient_name = ingredient_name,
        scientific_name = scientific_name,
        group = group,
        subGroup = subGroup
    )
}

fun Ingredient.toIngredientUiState(selected: Boolean = false): IngredientUiState {
    return IngredientUiState(
        ingredient = Ingredient(
            ingredient_id = ingredient_id,
            ingredient_name = ingredient_name,
            scientific_name = scientific_name,
            group = group,
            subGroup = subGroup),
        selected = selected)
}

fun List<Ingredient>.toIngredientUiStateList(): List<IngredientUiState> {
    return map { it.toIngredientUiState() }
}

fun PantryItem.toPantryEntity(): PantryEntity {
    return PantryEntity(
        item_id = id,
        item_name = name,
        bar_code = barCode,
        place_date = placeDate,
        expire_date = expireDate,
        quantity = quantity,
        unit = unit,
        ingredient_id = ingredient.ingredient_id
    )
}

fun List<PantryItem>.toPantryEntityList(): List<PantryEntity> {
    return map {
        it.toPantryEntity()
    }
}

fun PantryItem.toPantryItemUiState(): PantryItemUiState {
    return PantryItemUiState(
        pantryItem = PantryItem(
            id = id,
            name = name,
            barCode = barCode,
            placeDate = placeDate,
            expireDate = expireDate,
            quantity = quantity,
            unit = unit,
            ingredient = ingredient
        ),
        inputProductName = name,
        selectedOptionText = unit,
        sliderPosition = quantity
    )
}

fun PantryItemUiState.toPantryItem(): PantryItem {
    return pantryItem
}

fun List<PantryItemUiState>.toPantryItemList(): List<PantryItem> {
    return map { it.toPantryItem() }
}

fun List<PantryItem>.toPantryItemUiStateList(): List<PantryItemUiState> {
    return map { it.toPantryItemUiState() }
}

fun Ingredient.toPantryItem(): PantryItem {
    return PantryItem(
        id = 0,
        name = "Default $ingredient_name",
        placeDate = Calendar.getInstance().time,
        expireDate = Calendar.getInstance().time,
        quantity = 0.5f,
        unit = "KG",
        ingredient = Ingredient(
            ingredient_id = ingredient_id,
            ingredient_name = ingredient_name,
            scientific_name = scientific_name,
            group = group,
            subGroup = subGroup
        )
    )
}

fun RemoteIngredient.toIngredient(): Ingredient {
    return Ingredient(
        ingredient_id = id,
        ingredient_name = name,
        scientific_name = scientificName,
        group = group,
        subGroup = subGroup
    )
}

fun List<RemoteIngredient>.toIngredientsList(): List<Ingredient> {
    return map { it.toIngredient() }
}

fun RemotePantryItem.toPantryItem(): PantryItem {
    return PantryItem(
        id = id,
        name =name,
        barCode = barCode,
        //TODO
        placeDate = Calendar.getInstance().time,
        expireDate = Calendar.getInstance().time,
        quantity = quantity,
        unit = unit,
        ingredient = remoteIngredient.toIngredient()
    )
}

fun List<RemotePantryItem>.toPantryItemsList(): List<PantryItem> {
    return map { it.toPantryItem() }
}

fun PantryItem.toRemotePantryItemRequest(): RemotePantryItemRequest {
    return RemotePantryItemRequest(
        //TODO
        name = name,
        barCode = barCode ?: "000",
        placeDate = placeDate.time,
        expireDate = expireDate.time,
        quantity = quantity,
        unit = unit,
        ingredientId = ingredient.ingredient_id,
        userId = 1
    )
}

fun List<PantryItem>.toRemotePantryItemsList(): List<RemotePantryItemRequest>{
    return map { it.toRemotePantryItemRequest() }
}