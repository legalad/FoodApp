package com.example.foodapp.data.source.local

import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.Pantry
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

    override suspend fun addIngredientToPantry(pantry: Pantry) = withContext(ioDispatcher) {
        ingredientDao.addPantryItem(pantry)
    }

    override suspend fun addPantryItemList(pantryList: List<Pantry>) = withContext(ioDispatcher) {
        ingredientDao.addPantryItemList(pantryList)
    }
}