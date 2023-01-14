package com.example.foodapp.data.source

import com.example.foodapp.model.PantryItem

interface PantryRepository {

    suspend fun getPantryItems(): List<PantryItem>

    suspend fun addPantryItem(item: PantryItem)

    suspend fun updatePantryItem(item: PantryItem)

    suspend fun deletePantryItem(item: PantryItem)

}