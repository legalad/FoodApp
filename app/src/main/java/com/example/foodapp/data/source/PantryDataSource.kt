package com.example.foodapp.data.source

import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.Pantry

interface PantryDataSource {
    suspend fun getPantryItems(): Map<Pantry, Ingredient>

    suspend fun addPantryItems(vararg items: Pantry)

    suspend fun updatePantryItems(vararg items: Pantry)

    suspend fun deletePantryItems(vararg items: Pantry)

    suspend fun deletePantryItem(item: Pantry)
}