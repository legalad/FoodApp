package com.example.foodapp.data.source

import com.example.foodapp.model.PantryItem
import com.example.foodapp.data.Result
import kotlinx.coroutines.*

class DefaultPantryRepository (
    private val pantryRemoteDataSource: PantryDataSource,
    private val pantryLocalDataSource: PantryDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ): PantryRepository {

    override suspend fun getPantryItems(): Pair<Result<List<PantryItem>>, String> {
        val remote = CoroutineScope(ioDispatcher).async {
            pantryRemoteDataSource.getPantryItems()
        }
        val local = CoroutineScope(ioDispatcher).async {
            pantryLocalDataSource.getPantryItems()
        }

        val remoteResult = remote.await()
        val localResult = local.await()

        return when (remoteResult) {
            //TODO later add updating local datasource if fetched data from api, when all data in api will be inserted
            is Result.Success -> Pair(remoteResult, "Successful")
            is Result.Warning -> Pair(remoteResult, "Http warning occurred")
            is Result.Error ->
                when (localResult) {
                    is Result.Success -> Pair(Result.Warning(localResult.data), "Http error, using local database")
                    is Result.Warning -> Pair(Result.Warning(localResult.data), "Http error, local database warning")
                    is Result.Error -> Pair(localResult, "Http error, local database error")
                }
        }
    }

    override suspend fun addPantryItem(item: PantryItem) {
        CoroutineScope(ioDispatcher).launch{
            when (val result = pantryRemoteDataSource.addPantryItem(item)) {
                is Result.Success -> pantryLocalDataSource.addPantryItem(result.data)
                is Result.Warning -> ""
                is Result.Error -> pantryLocalDataSource.addPantryItem(item)
            }
        }
    }

    override suspend fun updatePantryItem(item: PantryItem) {
        CoroutineScope(ioDispatcher).launch{
            when (val result = pantryRemoteDataSource.updatePantryItem(item)) {
                is Result.Success -> pantryLocalDataSource.updatePantryItem(result.data)
                is Result.Warning -> ""
                is Result.Error -> pantryLocalDataSource.updatePantryItem(item)
            }
        }
    }
    override suspend fun deletePantryItem(item: PantryItem) {
        CoroutineScope(ioDispatcher).launch {
            when(val result = pantryRemoteDataSource.deletePantryItem(item)) {
                is Result.Success -> pantryLocalDataSource.deletePantryItem(result.data)
                is Result.Warning -> ""
                is Result.Error -> pantryLocalDataSource.deletePantryItem(item)
            }
        }
    }
}