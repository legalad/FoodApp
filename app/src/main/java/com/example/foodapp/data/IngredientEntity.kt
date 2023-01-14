package com.example.foodapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
data class IngredientEntity (
    @PrimaryKey(autoGenerate = true)
    val ingredient_id: Int,
    val ingredient_name: String,
    val scientific_name: String,
    val group: String,
    val subGroup: String
        )