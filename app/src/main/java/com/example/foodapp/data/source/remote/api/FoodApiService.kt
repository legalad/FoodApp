package com.example.foodapp.data.source.remote.api

import com.example.foodapp.data.source.remote.RemoteIngredient
import com.example.foodapp.data.source.remote.RemotePantryItem
import com.example.foodapp.data.source.remote.RemotePantryItemRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodApiService {
    @GET("/api/v1/auth/data/ingredients")
    suspend fun getIngredients(): List<RemoteIngredient>

    @GET("/api/v1/auth/pantry/items")
    suspend fun getPantry(): List<RemotePantryItem>

    @POST("/api/v1/auth/pantry/item")
    suspend fun postPantryItem(@Body remotePantryItemRequest: RemotePantryItemRequest): Response<String>

    @POST("/api/v1/auth/pantry/items")
    suspend fun postPantryItems(@Body remotePantryItemRequestList: List<RemotePantryItemRequest>)
    //problem with response
}