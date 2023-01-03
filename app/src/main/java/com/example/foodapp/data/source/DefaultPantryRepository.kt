package com.example.foodapp.data.source

import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.Pantry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultPantryRepository (
    private val pantryLocalDataSource: PantryDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ): PantryRepository {

    override suspend fun getPantryItems(): Map<Pantry, Ingredient> {
        return pantryLocalDataSource.getPantryItems()
    }

    override suspend fun addPantryItems(vararg items: Pantry) {
        pantryLocalDataSource.addPantryItems(*items)
    }

    override suspend fun updatePantryItems(vararg items: Pantry) {
        pantryLocalDataSource.updatePantryItems(*items)
    }

    override suspend fun deletePantryItems(vararg items: Pantry) {
        pantryLocalDataSource.deletePantryItems(*items)
    }

    override suspend fun deletePantryItem(item: Pantry) {
        pantryLocalDataSource.deletePantryItem(item)
    }
}