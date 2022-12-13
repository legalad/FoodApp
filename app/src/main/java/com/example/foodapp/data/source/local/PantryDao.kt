package com.example.foodapp.data.source.local

import androidx.room.Dao
import androidx.room.Query
import com.example.foodapp.data.Pantry

@Dao
interface PantryDao {
    @Query("SELECT * FROM PANTRY_TABLE ORDER BY id ASC")
    suspend fun getIngredients(): List<Pantry>
}