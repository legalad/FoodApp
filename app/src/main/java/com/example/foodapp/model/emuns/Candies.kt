package com.example.foodapp.model.emuns

enum class Candies :IngredientSpecificType {
    DONUT;

    override fun getValues(): List<String> {
        return values().map { item -> item.name }
    }
}