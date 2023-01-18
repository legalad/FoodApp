package com.example.foodapp.data.source.local

import android.database.SQLException
import com.example.foodapp.data.source.IngredientDataSource
import com.example.foodapp.data.utils.toIngredientList
import com.example.foodapp.data.utils.toPantryEntity
import com.example.foodapp.data.utils.toPantryEntityList
import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.PantryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.foodapp.data.Result

class IngredientLocalDataSource internal constructor(
    private val ingredientDao: IngredientDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IngredientDataSource {

    override suspend fun getIngredients(): Result<List<Ingredient>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(ingredientDao.getIngredients().toIngredientList())
        }
        catch (e: SQLException) {
            Result.Error(e)
        }

    }

    override suspend fun addIngredientToPantry(pantryItem: PantryItem): Result<PantryItem> = withContext(ioDispatcher) {

        return@withContext try {
            ingredientDao.addPantryItem(pantryItem.toPantryEntity())
            Result.Success(pantryItem)
        }
        catch (e: SQLException){
            Result.Error(e)
        }
    }

    override suspend fun addPantryItemList(pantryItemList: List<PantryItem>): Result<List<PantryItem>> = withContext(ioDispatcher) {
        return@withContext try {
            ingredientDao.addPantryItemList(pantryItemList.toPantryEntityList())
            Result.Success(pantryItemList)
        }
        catch (e: SQLException){
            Result.Error(e)
        }

    }
}