package com.example.foodapp.data.source

import com.example.foodapp.model.PantryItem
import com.example.foodapp.data.Result
interface PantryRepository {

    suspend fun getPantryItems(): Pair<Result<List<PantryItem>>, String>

    suspend fun addPantryItem(item: PantryItem)

    suspend fun updatePantryItem(item: PantryItem)

    suspend fun deletePantryItem(item: PantryItem)

}