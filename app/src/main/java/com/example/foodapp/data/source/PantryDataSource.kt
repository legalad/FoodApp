package com.example.foodapp.data.source

import com.example.foodapp.data.Pantry

interface PantryDataSource {
    suspend fun getPantry(): List<Pantry>
}