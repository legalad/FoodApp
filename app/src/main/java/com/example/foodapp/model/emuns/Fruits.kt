package com.example.foodapp.model.emuns

enum class Fruits : IngredientSpecificType {
    APPLE,
    BANANA,
    ORANGE,
    LEMON,
    LIME,
    MELON,
    WATERMELON,
    GRAPE,
    PLUM,
    MANGO,
    PINEAPPLE,
    KIWI,
    PEACH,
    AVOCADO,
    COCONUT,
    APRICOT,
    BLUEBERRY,
    BLACKBERRY,
    STRAWBERRY,
    RASPBERRY,
    CHERRY,
    PEAR,
    MANDARIN,
    DURIAN,
    PASSION_FRUIT,
    PERSIMMON,
    DRAGON_FRUIT,
    PAPAYA,
    POMEGRANATE,
    GRAPEFRUIT;

    override fun getValues(): List<String> {
        return values().map { item -> item.name }
    }
}