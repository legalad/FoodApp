package com.example.foodapp.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.foodapp.ui.state.FoodAppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class FoodAppViewModel : ViewModel() {
    private val _foodAppUiState = MutableStateFlow(FoodAppUiState())
    val foodAppUiState: StateFlow<FoodAppUiState> = _foodAppUiState.asStateFlow()
}