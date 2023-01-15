package com.example.foodapp.data.source.remote

import com.google.gson.annotations.SerializedName

data class RemotePantryItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("barCode")
    val barCode: String,
    @SerializedName("placeDate")
    val placeDate: String,
    @SerializedName("expireDate")
    val expireDate: String,
    @SerializedName("quantity")
    val quantity: Float,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("ingredient")
    val remoteIngredient: RemoteIngredient
)
