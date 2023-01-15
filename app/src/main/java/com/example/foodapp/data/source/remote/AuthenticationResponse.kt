package com.example.foodapp.data.source.remote

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @SerializedName("token")
    val token: String
)
