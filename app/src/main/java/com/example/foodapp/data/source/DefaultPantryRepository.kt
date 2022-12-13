package com.example.foodapp.data.source

import com.example.foodapp.data.Pantry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultPantryRepository (
    private val pantryLocalDataSource: PantryDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ): PantryRepository {
    override suspend fun getPantry(): List<Pantry> {
        return pantryLocalDataSource.getPantry()
    }
}