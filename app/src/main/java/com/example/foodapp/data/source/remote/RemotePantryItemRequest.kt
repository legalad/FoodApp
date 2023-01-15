package com.example.foodapp.data.source.remote

import com.google.gson.annotations.SerializedName

data class RemotePantryItemRequest (
    @SerializedName("name")
    val name: String,
    @SerializedName("barCode")
    val barCode: String,
    @SerializedName("placeDate")
    //TODO bład chyba w dacie
    val placeDate: Long,
    @SerializedName("expireDate")
    val expireDate: Long,
    @SerializedName("quantity")
    val quantity: Float,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("ingredientId")
    val ingredientId: Int,
    //delete later -> token provide user id
    @SerializedName("userId")
    val userId: Int
)