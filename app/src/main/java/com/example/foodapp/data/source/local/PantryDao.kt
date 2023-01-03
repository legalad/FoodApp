package com.example.foodapp.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.Pantry

@Dao
interface PantryDao {
    @Query("SELECT * FROM INGREDIENT_TABLE " +
    "JOIN PANTRY_TABLE ON pantry_table.ingredient_id = ingredient_table.id")
    suspend fun getPantryItems(): Map<Pantry, Ingredient>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPantryItems(vararg items: Pantry)

    @Update
    suspend fun updatePantryItems(vararg items: Pantry)

    @Delete
    suspend fun deletePantryItems(vararg items: Pantry)

    @Delete(entity = Pantry::class)
    suspend fun deletePantryItem(item: Pantry)
}