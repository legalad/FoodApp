package com.example.foodapp.data.source

import com.example.foodapp.data.Pantry

interface PantryRepository {

    suspend fun getPantry(): List<Pantry>

}