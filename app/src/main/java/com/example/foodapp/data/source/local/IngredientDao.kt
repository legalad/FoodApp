package com.example.foodapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.PantryEntity

@Dao
interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPantryItem(pantryEntity: PantryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPantryItemList(pantryEntityList: List<PantryEntity>)

    @Query("SELECT * FROM INGREDIENT_TABLE ORDER BY ingredient_id ASC")
    suspend fun getIngredients(): List<IngredientEntity>
}