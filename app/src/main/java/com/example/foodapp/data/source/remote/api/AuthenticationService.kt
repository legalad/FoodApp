package com.example.foodapp.data.source.remote.api

import com.example.foodapp.data.source.remote.AuthenticationRequest
import com.example.foodapp.data.source.remote.AuthenticationResponse
import com.example.foodapp.data.source.remote.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("/api/v1/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<AuthenticationResponse>

    @POST("/api/v1/auth/authenticate")
    suspend fun authenticate(@Body authenticationRequest: AuthenticationRequest): Response<AuthenticationResponse>

}