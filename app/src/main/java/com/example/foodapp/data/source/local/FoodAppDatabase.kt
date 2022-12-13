package com.example.foodapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.Pantry
import com.example.foodapp.data.utils.Converters

@Database(entities = [Ingredient::class, Pantry::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FoodAppDatabase : RoomDatabase() {
    abstract fun ingredientDao(): IngredientDao
    abstract fun pantryDao(): PantryDao
}