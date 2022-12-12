package com.example.foodapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodapp.data.Ingredient

@Database(entities = [Ingredient::class], version = 1, exportSchema = false)
abstract class FoodAppDatabase : RoomDatabase() {
    abstract fun ingredientDao(): IngredientDao
}