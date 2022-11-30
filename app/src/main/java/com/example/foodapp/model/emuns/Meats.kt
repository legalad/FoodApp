package com.example.foodapp.model.emuns

enum class Meats :IngredientSpecificType {
    PORK,
    BEEF,
    LAMB,
    GOAT,
    VENISON,
    CHICKEN,
    TURKEY,
    DUCK;

    override fun getValues(): List<String> {
        return values().map { item -> item.name }
    }

}