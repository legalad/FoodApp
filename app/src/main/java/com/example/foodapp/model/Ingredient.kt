package com.example.foodapp.model

data class Ingredient (
    val ingredient_id: Int,
    val ingredient_name: String,
    val scientific_name: String,
    val group: String,
    val subGroup: String
)