package com.example.foodapp.data.source.remote

import com.example.foodapp.data.source.PantryDataSource
import com.example.foodapp.data.source.remote.api.FoodApiService
import com.example.foodapp.data.utils.toPantryItemsList
import com.example.foodapp.data.utils.toRemotePantryItemRequest
import com.example.foodapp.model.PantryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PantryRemoteDataSource internal constructor(
private val foodApiService: FoodApiService,
private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): PantryDataSource {
    override suspend fun getPantryItems(): List<PantryItem> = withContext(ioDispatcher) {
        foodApiService.getPantry().toPantryItemsList()
    }

    override suspend fun addPantryItem(item: PantryItem) {
        foodApiService.postPantryItem(item.toRemotePantryItemRequest())
    }

    override suspend fun updatePantryItem(item: PantryItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePantryItem(item: PantryItem) {
        TODO("Not yet implemented")
    }

}