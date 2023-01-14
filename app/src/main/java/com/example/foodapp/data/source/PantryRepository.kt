package com.example.foodapp.data.source

import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.PantryEntity

interface PantryRepository {

    suspend fun getPantryItems(): Map<PantryEntity, IngredientEntity>

    suspend fun addPantryItems(vararg items: PantryEntity)

    suspend fun updatePantryItems(vararg items: PantryEntity)

    suspend fun deletePantryItems(vararg items: PantryEntity)

    suspend fun deletePantryItem(item: PantryEntity)

}