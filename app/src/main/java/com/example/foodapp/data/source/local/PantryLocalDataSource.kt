package com.example.foodapp.data.source.local

import android.database.SQLException
import com.example.foodapp.data.source.PantryDataSource
import com.example.foodapp.data.utils.toPantryEntity
import com.example.foodapp.data.utils.toPantryItemList
import com.example.foodapp.model.PantryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.foodapp.data.Result

class PantryLocalDataSource internal constructor(
    private val pantryDao: PantryDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PantryDataSource{

    override suspend fun getPantryItems(): Result<List<PantryItem>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(pantryDao.getPantryItems().toPantryItemList())
        }
        catch (e: SQLException){
            Result.Error(e)
        }
    }

    override suspend fun addPantryItem(item: PantryItem): Result<PantryItem> = withContext(ioDispatcher) {
        return@withContext try {
            pantryDao.addPantryItem(item.toPantryEntity())
            Result.Success(item)
        }
        catch (e: SQLException){
            Result.Error(e)
        }

    }

    override suspend fun updatePantryItem(item: PantryItem): Result<PantryItem> = withContext(ioDispatcher) {
        return@withContext try {
            pantryDao.updatePantryItem(item.toPantryEntity())
            Result.Success(item)
        }
        catch (e: SQLException){
            Result.Error(e)
        }
    }


    override suspend fun deletePantryItem(item: PantryItem): Result<PantryItem> = withContext(ioDispatcher) {
        return@withContext try {
            pantryDao.deletePantryItem(item.toPantryEntity())
            Result.Success(item)
        }
        catch (e: SQLException){
            Result.Error(e)
        }
    }


}