package com.example.foodapp.data.source

import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.PantryEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultIngredientRepository (
    private val ingredientRemoteDataSource: IngredientDataSource,
    private val ingredientLocalDataSource: IngredientDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ) : IngredientRepository {

    override suspend fun getIngredients(): List<IngredientEntity> {
        return ingredientLocalDataSource.getIngredients()
    }

    override suspend fun addIngredientToPantry(pantryEntity: PantryEntity) {
        ingredientLocalDataSource.addIngredientToPantry(pantryEntity)
    }

    override suspend fun addPantryItemList(pantryEntityList: List<PantryEntity>) {
        ingredientLocalDataSource.addPantryItemList(pantryEntityList)
    }
}