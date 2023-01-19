package com.example.foodapp.data.source.remote

import com.example.foodapp.data.source.PantryDataSource
import com.example.foodapp.data.source.remote.api.FoodApiService
import com.example.foodapp.data.utils.toPantryItemsList
import com.example.foodapp.data.utils.toRemotePantryItemRequest
import com.example.foodapp.model.PantryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import com.example.foodapp.data.Result
import com.example.foodapp.data.utils.toPantryItem

class PantryRemoteDataSource internal constructor(
private val foodApiService: FoodApiService,
private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PantryDataSource {
    override suspend fun getPantryItems(): Result<List<PantryItem>> = withContext(ioDispatcher) {
        return@withContext try {
            val response = foodApiService.getPantry()
            val body = response.body()?.toPantryItemsList()
            if (body is List<PantryItem>) Result.Success(body)
            else Result.Error(HttpException(response))
        }
        catch (e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun addPantryItem(item: PantryItem): Result<PantryItem> = withContext(ioDispatcher) {
        return@withContext try {
            val response = foodApiService.postPantryItem(item.toRemotePantryItemRequest())
            val body = response.body()?.toPantryItem()
            if (body is PantryItem) Result.Success(body)
            else Result.Error(HttpException(response))
        }
        catch (e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun updatePantryItem(item: PantryItem): Result<PantryItem> = withContext(ioDispatcher) {
        return@withContext try {
            val response = foodApiService.putPantryItem(item.id ,item.toRemotePantryItemRequest())
            val body = response.body()?.toPantryItem()
            if (body is PantryItem) Result.Success(body)
            else Result.Error(HttpException(response))
        }
        catch (e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun deletePantryItem(item: PantryItem): Result<PantryItem> = withContext(ioDispatcher) {
        return@withContext try {
            val response = foodApiService.deletePantryItem(item.id)
            val body = response.body()?.toPantryItem()
            if (body is PantryItem) Result.Success(body)
            else Result.Error(HttpException(response))
        }
        catch (e: Exception){
            Result.Error(e)
        }
    }

}