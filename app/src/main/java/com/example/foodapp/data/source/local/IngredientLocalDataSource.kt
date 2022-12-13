package com.example.foodapp.data.source.local

import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.source.IngredientDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IngredientLocalDataSource internal constructor(
    private val ingredientDao: IngredientDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IngredientDataSource {

    override suspend fun getIngredients(): List<Ingredient> = withContext(ioDispatcher) {
        ingredientDao.getIngredients()
    }

    override suspend fun addIngredient(ingredient: Ingredient) = withContext(ioDispatcher) {
        ingredientDao.addIngredient(ingredient)
    }
}