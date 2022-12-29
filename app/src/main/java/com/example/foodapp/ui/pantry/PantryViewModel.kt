package com.example.foodapp.ui.pantry

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.source.PantryRepository
import com.example.foodapp.data.utils.Mappers
import com.example.foodapp.model.PantryItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantryViewModel @Inject constructor(
    private val pantryRepository: PantryRepository
): ViewModel() {
    private val _pantryUiState = MutableStateFlow(PantryUiState())

    init {
        viewModelScope.launch {
            val pantryItemsMapWithState: MutableMap<PantryItemUiState, Ingredient> = mutableMapOf()
            pantryRepository.getPantryItems().forEach {
                    (pantry, ingredient) -> pantryItemsMapWithState[Mappers.fromPantryItemToPantryItemUiState(pantry)] =
                ingredient }
            _pantryUiState.update {
                it.copy(
                    pantryItemsList = pantryItemsMapWithState
                )
            }
            Log.d("SPRAWDZAM", "INIT COMPLETE - ${pantryRepository.getPantryItems().size}")
        }
    }




}