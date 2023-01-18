package com.example.foodapp.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.PantryEntity

@Dao
interface PantryDao {
    @Query("SELECT * FROM INGREDIENT_TABLE " +
            "JOIN PANTRY_TABLE ON pantry_table.ingredient_id = ingredient_table.ingredient_id"
    )
    suspend fun getPantryItems(): Map<PantryEntity, IngredientEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPantryItem(item: PantryEntity)

    @Update
    suspend fun updatePantryItem(item: PantryEntity)

    @Delete(entity = PantryEntity::class)
    suspend fun deletePantryItem(item: PantryEntity)

}