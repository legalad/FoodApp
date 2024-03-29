package com.example.foodapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class FoodAppViewModel : ViewModel() {
    private val _foodAppUiState = MutableStateFlow(FoodAppUiState())
    val foodAppUiState: StateFlow<FoodAppUiState> = _foodAppUiState.asStateFlow()
}