package com.example.foodapp.data.source.remote

import com.google.gson.annotations.SerializedName

data class AuthenticationRequest (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)