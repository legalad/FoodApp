package com.example.foodapp.data.source

import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.PantryItem
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

    override suspend fun addIngredientToPantry(pantryItem: PantryItem) {
        ingredientLocalDataSource.addIngredientToPantry(pantryItem)
    }

    override suspend fun addPantryItemList(pantryItemList: List<PantryItem>) {
        ingredientLocalDataSource.addPantryItemList(pantryItemList)
    }
}