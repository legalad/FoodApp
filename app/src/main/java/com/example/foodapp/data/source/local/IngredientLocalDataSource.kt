package com.example.foodapp.data.source.local

import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.PantryEntity
import com.example.foodapp.data.source.IngredientDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IngredientLocalDataSource internal constructor(
    private val ingredientDao: IngredientDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IngredientDataSource {

    override suspend fun getIngredients(): List<IngredientEntity> = withContext(ioDispatcher) {
        ingredientDao.getIngredients()
    }

    override suspend fun addIngredientToPantry(pantryEntity: PantryEntity) = withContext(ioDispatcher) {
        ingredientDao.addPantryItem(pantryEntity)
    }

    override suspend fun addPantryItemList(pantryEntityList: List<PantryEntity>) = withContext(ioDispatcher) {
        ingredientDao.addPantryItemList(pantryEntityList)
    }
}