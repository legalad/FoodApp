package com.example.foodapp.data.source

import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.PantryEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultPantryRepository (
    private val pantryLocalDataSource: PantryDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ): PantryRepository {

    override suspend fun getPantryItems(): Map<PantryEntity, IngredientEntity> {
        return pantryLocalDataSource.getPantryItems()
    }

    override suspend fun addPantryItems(vararg items: PantryEntity) {
        pantryLocalDataSource.addPantryItems(*items)
    }

    override suspend fun updatePantryItems(vararg items: PantryEntity) {
        pantryLocalDataSource.updatePantryItems(*items)
    }

    override suspend fun deletePantryItems(vararg items: PantryEntity) {
        pantryLocalDataSource.deletePantryItems(*items)
    }

    override suspend fun deletePantryItem(item: PantryEntity) {
        pantryLocalDataSource.deletePantryItem(item)
    }
}