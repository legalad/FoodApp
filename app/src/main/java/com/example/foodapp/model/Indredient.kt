package com.example.foodapp.model

data class Ingredient(
    override val name: String,
    override val type: String,
    val quantity: Float,
    val unit: Units
    ) : Item(name, type)
