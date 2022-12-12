package com.example.foodapp.data.source

import com.example.foodapp.data.Ingredient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultIngredientRepository (
    private val ingredientRemoteDataSource: IngredientDataSource,
    private val ingredientLocalDataSource: IngredientDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ) : IngredientRepository {

    override suspend fun getIngredients(): Result<List<Ingredient>> {
        TODO("Not yet implemented")
    }

}