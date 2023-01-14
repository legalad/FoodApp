package com.example.foodapp.model

import java.util.*

data class PantryItem(
    val id: Int,
    val name: String,
    val barCode: String? = null,
    val placeDate: Date,
    val expireDate: Date,
    val quantity: Float,
    val unit: String,
    val ingredient: Ingredient
)
