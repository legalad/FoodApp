package com.example.foodapp.model.emuns

enum class Spices :IngredientSpecificType {
    PEPPER;
    override fun getValues(): List<String> {
        return values().map { item -> item.name }
    }
}