package com.example.foodapp.data.source.remote.api

import com.example.foodapp.data.source.remote.RemoteIngredient
import com.example.foodapp.data.source.remote.RemotePantryItem
import com.example.foodapp.data.source.remote.RemotePantryItemRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FoodApiService : AuthenticationService {
    @GET("/api/v1/auth/data/ingredients")
    suspend fun getIngredients(): List<RemoteIngredient>

    @GET("/api/v1/auth/pantry/items")
    suspend fun getPantry(): List<RemotePantryItem>

    @GET("/api/v1/auth/pantry/item/{id}")
    public suspend fun getPantryItem(@Path("id") id: Int): Response<RemoteIngredient>

    @POST("/api/v1/auth/pantry/item")
    suspend fun postPantryItem(@Body remotePantryItemRequest: RemotePantryItemRequest): Response<String>

    @POST("/api/v1/auth/pantry/items")
    suspend fun postPantryItems(@Body remotePantryItemRequestList: List<RemotePantryItemRequest>)
    //problem with response

    @PUT("/api/v1/auth/pantry/item/{id}")
    suspend fun putPantryItem(@Path("id") id: Int, @Body remotePantryItemRequest: RemotePantryItemRequest): Response<String>

    @DELETE("/api/v1/auth/pantry/item/{id}")
    suspend fun deletePantryItem(@Path("id") id: Int): Response<String>

}