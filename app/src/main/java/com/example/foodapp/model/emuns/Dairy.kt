package com.example.foodapp.model.emuns

enum class Dairy :IngredientSpecificType {
    YOGHURT,
    MILK,
    CHEESE,
    WHEY_PROTEIN,
    CREAM,
    BUTTER,
    ICE_CREAM;

    override fun getValues(): List<String> {
        return values().map { item -> item.name }
    }
}