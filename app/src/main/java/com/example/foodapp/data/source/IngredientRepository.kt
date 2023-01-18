package com.example.foodapp.data.source

import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.PantryItem
import com.example.foodapp.data.Result

interface IngredientRepository {

    suspend fun getIngredients(): Pair<Result<List<Ingredient>>, String>

    suspend fun addIngredientToPantry(pantryItem: PantryItem)

    suspend fun addPantryItemList(pantryItemList: List<PantryItem>)

}
