package com.example.foodapp.data.source.remote

import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.PantryEntity
import com.example.foodapp.data.source.IngredientDataSource

object IngredientRemoteDataSource : IngredientDataSource {

    override suspend fun getIngredients(): List<IngredientEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun addIngredientToPantry(pantryEntity: PantryEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun addPantryItemList(pantryEntityList: List<PantryEntity>) {
        TODO("Not yet implemented")
    }
}