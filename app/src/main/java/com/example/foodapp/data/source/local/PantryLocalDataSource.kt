package com.example.foodapp.data.source.local

import com.example.foodapp.data.source.PantryDataSource
import com.example.foodapp.data.utils.toPantryEntity
import com.example.foodapp.data.utils.toPantryItemList
import com.example.foodapp.model.PantryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PantryLocalDataSource internal constructor(
    private val pantryDao: PantryDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PantryDataSource{

    override suspend fun getPantryItems(): List<PantryItem> = withContext(ioDispatcher) {
        pantryDao.getPantryItems().toPantryItemList()
    }

    override suspend fun addPantryItem(item: PantryItem) {
        pantryDao.addPantryItem(item.toPantryEntity())
    }

    override suspend fun updatePantryItem(item: PantryItem) {
        pantryDao.updatePantryItem(item.toPantryEntity())
    }


    override suspend fun deletePantryItem(item: PantryItem) = withContext(ioDispatcher) {
        pantryDao.deletePantryItem(item.toPantryEntity())
    }


}