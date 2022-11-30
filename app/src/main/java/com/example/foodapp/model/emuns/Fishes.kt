package com.example.foodapp.model.emuns

enum class Fishes :IngredientSpecificType {
    SALMON,
    TUNA,
    COD,
    CATFISH;

    override fun getValues(): List<String> {
        return values().map { item -> item.name }
    }
}