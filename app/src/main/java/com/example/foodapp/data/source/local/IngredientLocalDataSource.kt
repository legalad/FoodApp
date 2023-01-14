package com.example.foodapp.data.source.local

import com.example.foodapp.data.source.IngredientDataSource
import com.example.foodapp.data.utils.toIngredientList
import com.example.foodapp.data.utils.toPantryEntity
import com.example.foodapp.data.utils.toPantryEntityList
import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.PantryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IngredientLocalDataSource internal constructor(
    private val ingredientDao: IngredientDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IngredientDataSource {

    override suspend fun getIngredients(): List<Ingredient> = withContext(ioDispatcher) {
        ingredientDao.getIngredients().toIngredientList()
    }

    override suspend fun addIngredientToPantry(pantryItem: PantryItem) = withContext(ioDispatcher) {
        ingredientDao.addPantryItem(pantryItem.toPantryEntity())
    }

    override suspend fun addPantryItemList(pantryItemList: List<PantryItem>) = withContext(ioDispatcher) {
        ingredientDao.addPantryItemList(pantryItemList.toPantryEntityList())
    }
}