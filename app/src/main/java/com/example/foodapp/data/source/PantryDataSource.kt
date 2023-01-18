package com.example.foodapp.data.source

import com.example.foodapp.model.PantryItem
import com.example.foodapp.data.Result

interface PantryDataSource {
    suspend fun getPantryItems(): Result<List<PantryItem>>

    suspend fun addPantryItem(item: PantryItem): Result<PantryItem>

    suspend fun updatePantryItem(item: PantryItem): Result<PantryItem>

    suspend fun deletePantryItem(item: PantryItem)
}