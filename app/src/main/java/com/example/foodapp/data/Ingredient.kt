package com.example.foodapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
data class Ingredient (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val scientific_name: String,
    val group: String,
    val subGroup: String
        )