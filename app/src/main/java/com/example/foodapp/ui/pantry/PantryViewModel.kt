package com.example.foodapp.ui.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.source.PantryRepository
import com.example.foodapp.data.utils.Mappers
import com.example.foodapp.model.PantryItemUiState
import com.example.foodapp.ui.ingredients.IngredientTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PantryViewModel @Inject constructor(
    private val pantryRepository: PantryRepository
): ViewModel() {
    private val _pantryUiState = MutableStateFlow(PantryUiState())
    val pantryUiState: StateFlow<PantryUiState> = _pantryUiState

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
        }
    }

    fun getFilteredPantryItemList(type: IngredientTypes): Map<PantryItemUiState, Ingredient> {
        return filterIngredientList (type.filterPantry)
    }

    private fun filterIngredientList(f: (Map<PantryItemUiState, Ingredient>) -> Map<PantryItemUiState, Ingredient>): Map<PantryItemUiState, Ingredient>{
        return f(_pantryUiState.value.pantryItemsList)
    }




}