package com.example.foodapp.data.source.remote

import com.google.gson.annotations.SerializedName

data class RemoteIngredient (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("scientificName")
    val scientificName: String,
    @SerializedName("group")
    val group: String,
    @SerializedName("subGroup")
    val subGroup: String
)