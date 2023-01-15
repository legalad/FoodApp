package com.example.foodapp.data.source

import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.PantryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.net.ConnectException

class DefaultIngredientRepository (
    private val ingredientRemoteDataSource: IngredientDataSource,
    private val ingredientLocalDataSource: IngredientDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ) : IngredientRepository {

    override suspend fun getIngredients(): List<Ingredient> {
        return try {
            ingredientRemoteDataSource.getIngredients()
        } catch (e: ConnectException) {
            ingredientLocalDataSource.getIngredients()
        }

    }

    override suspend fun addIngredientToPantry(pantryItem: PantryItem) {
        ingredientLocalDataSource.addIngredientToPantry(pantryItem)
        ingredientRemoteDataSource.addIngredientToPantry(pantryItem)
    }

    override suspend fun addPantryItemList(pantryItemList: List<PantryItem>) {
        ingredientRemoteDataSource.addPantryItemList(pantryItemList)
        ingredientLocalDataSource.addPantryItemList(pantryItemList)
    }
}