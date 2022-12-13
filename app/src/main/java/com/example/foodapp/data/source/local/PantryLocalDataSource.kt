package com.example.foodapp.data.source.local

import com.example.foodapp.data.Pantry
import com.example.foodapp.data.source.PantryDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PantryLocalDataSource internal constructor(
    private val pantryDao: PantryDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PantryDataSource{
    override suspend fun getPantry(): List<Pantry> = withContext(ioDispatcher){
        pantryDao.getIngredients()
    }
}