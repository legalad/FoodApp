package com.example.foodapp.model.emuns

enum class Drinks :IngredientSpecificType {
    WATER;

    override fun getValues(): List<String> {
        return values().map { item -> item.name }
    }
}