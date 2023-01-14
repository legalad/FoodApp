package com.example.foodapp.data.source

import com.example.foodapp.model.PantryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultPantryRepository (
    private val pantryLocalDataSource: PantryDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ): PantryRepository {

    override suspend fun getPantryItems(): List<PantryItem> {
        return pantryLocalDataSource.getPantryItems()
    }

    override suspend fun addPantryItem(item: PantryItem) {
        pantryLocalDataSource.addPantryItem(item)
    }

    override suspend fun updatePantryItem(item: PantryItem) {
        pantryLocalDataSource.updatePantryItem(item)
    }


    override suspend fun deletePantryItem(item: PantryItem) {
        pantryLocalDataSource.deletePantryItem(item)
    }
}