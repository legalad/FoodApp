package com.example.foodapp.model.emuns

enum class Seafood :IngredientSpecificType {
    SHRIMP,
    SCALLOP,
    CLAM,
    MUSSEL,
    OCTOPUS,
    LOBSTER,
    CRAYFISH,
    CRAB;
    override fun getValues(): List<String> {
        return values().map { item -> item.name }
    }
}