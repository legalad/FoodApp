package com.example.foodapp.data.source.remote

import android.util.Log
import com.example.foodapp.data.source.IngredientDataSource
import com.example.foodapp.data.source.remote.api.FoodApiService
import com.example.foodapp.data.utils.toIngredientsList
import com.example.foodapp.data.utils.toRemotePantryItemRequest
import com.example.foodapp.data.utils.toRemotePantryItemsList
import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.PantryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IngredientRemoteDataSource internal constructor(
    private val foodApiService: FoodApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): IngredientDataSource {

    override suspend fun getIngredients(): List<Ingredient> = withContext(ioDispatcher) {
       foodApiService.getIngredients().toIngredientsList()
    }

    override suspend fun addIngredientToPantry(pantryItem: PantryItem) {
        foodApiService.postPantryItem(pantryItem.toRemotePantryItemRequest())
        Log.d("foodapp", "")
    }

    override suspend fun addPantryItemList(pantryItemList: List<PantryItem>) {
        foodApiService.postPantryItems(pantryItemList.toRemotePantryItemsList())
    }
}