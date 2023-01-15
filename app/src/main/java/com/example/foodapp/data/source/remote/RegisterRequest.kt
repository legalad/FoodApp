package com.example.foodapp.data.source.remote

import com.google.gson.annotations.SerializedName

data class RegisterRequest (
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)