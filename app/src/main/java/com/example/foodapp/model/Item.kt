package com.example.foodapp.model

sealed class Item(
    open val name: String,
    open val type: String
) {

}
