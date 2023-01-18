package com.example.foodapp.data.source.remote

import android.util.Log
import com.example.foodapp.data.source.IngredientDataSource
import com.example.foodapp.data.source.remote.api.FoodApiService
import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.PantryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import com.example.foodapp.data.Result
import com.example.foodapp.data.utils.*

class IngredientRemoteDataSource internal constructor(
    private val foodApiService: FoodApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): IngredientDataSource {

    override suspend fun getIngredients(): Result<List<Ingredient>> = withContext(ioDispatcher) {

        return@withContext try {
            val response = foodApiService.getIngredients()
            Log.d("BŁAD", response.toString())
            val body = response.body()?.toIngredientsList()
            if (body is List<Ingredient>) Result.Success(body)
            else Result.Error(HttpException(response))
        }
        catch (e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun addIngredientToPantry(pantryItem: PantryItem): Result<PantryItem> = withContext(ioDispatcher) {
        return@withContext try {
            val response = foodApiService.postPantryItem(pantryItem.toRemotePantryItemRequest())
            val body = response.body()?.toPantryItem()
            if (body is PantryItem) Result.Success(body)
            else Result.Error(HttpException(response))
        }
        catch (e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun addPantryItemList(pantryItemList: List<PantryItem>): Result<List<PantryItem>> = withContext(ioDispatcher) {
        return@withContext try {
            val response = foodApiService.postPantryItems(pantryItemList.toRemotePantryItemsList())
            val body = response.body()?.toPantryItemsList()
            if (body is List<PantryItem>) Result.Success(body)
            else Result.Error(HttpException(response))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}