package com.example.foodapp.data.source.local

import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.source.IngredientDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class IngredientLocalDataSource internal constructor(
    private val ingredientDao: IngredientDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IngredientDataSource {

    override suspend fun getIngredients(): Result<List<Ingredient>> {
        TODO("Not yet implemented")
    }
}