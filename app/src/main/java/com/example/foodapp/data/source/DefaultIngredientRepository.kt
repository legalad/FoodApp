package com.example.foodapp.data.source

import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.PantryItem
import com.example.foodapp.data.Result
import com.example.foodapp.data.Result.Success
import com.example.foodapp.data.Result.Warning
import com.example.foodapp.data.Result.Error
import kotlinx.coroutines.*

class DefaultIngredientRepository (
    private val ingredientRemoteDataSource: IngredientDataSource,
    private val ingredientLocalDataSource: IngredientDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ) : IngredientRepository {

    override suspend fun getIngredients(): Pair<Result<List<Ingredient>>, String> {

        val remote = CoroutineScope(ioDispatcher).async {
            ingredientRemoteDataSource.getIngredients()
        }
        val local = CoroutineScope(ioDispatcher).async {
            ingredientLocalDataSource.getIngredients()
        }

        val remoteResult = remote.await()
        val localResult = local.await()
        return when (remoteResult) {
            is Success -> Pair(remoteResult, "Successful")
            is Warning -> Pair(remoteResult, "Http warning occurred")
            is Error ->
                when (localResult) {
                    is Success -> Pair(Warning(localResult.data), "Http error, using local database")
                    is Warning -> Pair(Warning(localResult.data), "Http error, local database warning")
                    is Error -> Pair(localResult, "Http error, local database error")
                }
        }
    }

    override suspend fun addIngredientToPantry(pantryItem: PantryItem) {
        CoroutineScope(ioDispatcher).launch {
            when (val result = ingredientRemoteDataSource.addIngredientToPantry(pantryItem)) {
                is Success -> ingredientLocalDataSource.addIngredientToPantry(result.data)
                is Warning -> ""
                is Error -> ingredientLocalDataSource.addIngredientToPantry(pantryItem)
            }
        }
    }

    override suspend fun addPantryItemList(pantryItemList: List<PantryItem>) {
        CoroutineScope(ioDispatcher).launch{
            when (val result = ingredientRemoteDataSource.addPantryItemList(pantryItemList)) {
                is Success -> ingredientLocalDataSource.addPantryItemList(result.data)
                is Warning -> ""
                is Error -> ingredientLocalDataSource.addPantryItemList(pantryItemList)
            }
        }
    }
}