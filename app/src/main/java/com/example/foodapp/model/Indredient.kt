package com.example.foodapp.model

data class Ingredient(
    override val name: String,
    val scientificName: String,
    val group: String,
    val subGroup: String,
    ) : Item(name)
