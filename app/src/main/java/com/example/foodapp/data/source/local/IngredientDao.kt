package com.example.foodapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.Pantry

@Dao
interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPantryItem(pantry: Pantry)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPantryItemList(pantryList: List<Pantry>)

    @Query("SELECT * FROM INGREDIENT_TABLE ORDER BY id ASC")
    suspend fun getIngredients(): List<Ingredient>
}