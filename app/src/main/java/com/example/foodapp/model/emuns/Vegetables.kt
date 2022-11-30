package com.example.foodapp.model.emuns

enum class Vegetables :IngredientSpecificType {
    POTATO,
    CARROT,
    ONION,
    MUSHROOM,
    CABBAGE,
    BROCCOLI,
    CAULIFLOWER,
    SWEET_POTATO,
    LEEK,
    GARLIC,
    PARSNIP,
    RADISH,
    SPINACH,
    LETTUCE,
    CORN,
    PEPPER,
    EGGPLANT,
    ZUCCHINI,
    CELERY,
    GREEN_BEANS,
    PEAS,
    OLIVE,
    BEETROOT,
    SPROUTS,
    RED_ONION,
    CUCUMBER,
    ASPARAGUS,
    KALE,
    BAMBOO_SHOOT,
    LOTUS_ROOT;
    override fun getValues(): List<String> {
        return values().map { item -> item.name }
    }
}