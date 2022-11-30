package com.example.foodapp.model.emuns

enum class Grain :IngredientSpecificType {
    BULGUR,
    CORN,
    OATS,
    RICE,
    SORGHUM,
    WHEAT,
    BARLEY;
    override fun getValues(): List<String> {
        return values().map { item -> item.name }
    }
}