package com.example.foodapp.data.source.local

import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.PantryEntity
import com.example.foodapp.data.source.PantryDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PantryLocalDataSource internal constructor(
    private val pantryDao: PantryDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PantryDataSource{

    override suspend fun getPantryItems(): Map<PantryEntity, IngredientEntity> = withContext(ioDispatcher) {
        pantryDao.getPantryItems()
    }

    override suspend fun addPantryItems(vararg items: PantryEntity) {
        pantryDao.addPantryItems(*items)
    }

    override suspend fun updatePantryItems(vararg items: PantryEntity) {
        pantryDao.updatePantryItems(*items)
    }

    override suspend fun deletePantryItems(vararg items: PantryEntity) = withContext(ioDispatcher) {
        pantryDao.deletePantryItems(*items)
    }

    override suspend fun deletePantryItem(item: PantryEntity) = withContext(ioDispatcher) {
        pantryDao.deletePantryItem(item)
    }


}