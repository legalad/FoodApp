package com.example.foodapp.data.source.local

import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.Pantry
import com.example.foodapp.data.source.PantryDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PantryLocalDataSource internal constructor(
    private val pantryDao: PantryDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PantryDataSource{

    override suspend fun getPantryItems(): Map<Pantry, Ingredient> = withContext(ioDispatcher) {
        pantryDao.getPantryItems()
    }

    override suspend fun addPantryItems(vararg items: Pantry) {
        pantryDao.addPantryItems(*items)
    }

    override suspend fun updatePantryItems(vararg items: Pantry) {
        pantryDao.updatePantryItems(*items)
    }

    override suspend fun deletePantryItems(vararg items: Pantry) {
        pantryDao.deletePantryItems(*items)
    }


}