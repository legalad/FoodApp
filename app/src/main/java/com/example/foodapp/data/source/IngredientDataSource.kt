package com.example.foodapp.data.source

import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.PantryEntity

interface IngredientDataSource {

    suspend fun getIngredients(): List<IngredientEntity>

    suspend fun addIngredientToPantry(pantryEntity: PantryEntity)

    suspend fun addPantryItemList(pantryEntityList: List<PantryEntity>)

}