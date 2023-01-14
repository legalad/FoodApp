package com.example.foodapp.model

import com.example.foodapp.data.IngredientEntity
import java.util.*

data class PantryItem(
    val item_id: Int,
    val item_name: String,
    val bar_code: String? = null,
    val place_date: Date,
    val expire_date: Date,
    val quantity: Float,
    val unit: String,
    val ingredientEntity: IngredientEntity
)
