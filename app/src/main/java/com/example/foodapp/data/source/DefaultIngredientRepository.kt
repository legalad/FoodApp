package com.example.foodapp.data.source

import com.example.foodapp.data.Ingredient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultIngredientRepository (
    private val ingredientRemoteDataSource: IngredientDataSource,
    private val ingredientLocalDataSource: IngredientDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ) : IngredientRepository {

    override suspend fun getIngredients(): List<Ingredient> {
        return ingredientLocalDataSource.getIngredients()
    }

    override suspend fun addIngredient(ingredient: Ingredient) {
        ingredientLocalDataSource.addIngredient(ingredient)
    }

}