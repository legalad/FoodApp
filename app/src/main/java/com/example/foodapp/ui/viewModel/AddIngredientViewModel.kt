package com.example.foodapp.ui.viewModel

import android.util.Log
import com.example.foodapp.data.source.IngredientRepository
import com.example.foodapp.ui.screens.IngredientTypes
import com.example.foodapp.ui.state.AddIngredientUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddIngredientViewModel @Inject constructor(
    private val ingredientRepository: IngredientRepository
) : FoodAppViewModel() {
    private val _addIngredientUiState = MutableStateFlow(AddIngredientUiState())
    val addIngredientUiState: StateFlow<AddIngredientUiState> = _addIngredientUiState

    fun changeSelectedTab(selectedTab: IngredientTypes) {
        _addIngredientUiState.update {
            it.copy(selectedType = selectedTab)
        }
    }

    fun onSearchTextFieldValueChange (input: String) {
        _addIngredientUiState.update {
            it.copy(
                input = input
            )
        }
        Log.d("DANE", _addIngredientUiState.value.selectedType.name)
    }

    fun onCancelClicked() {
        _addIngredientUiState.update {

            it.copy(
                input = it.input.drop(it.input.length)
            )
        }
    }

    fun hideKeyboard() {
        _addIngredientUiState.update {
            it.copy(
                hideKeyboard = true
            )
        }
    }

    fun onFocusClear() {
        _addIngredientUiState.update {
            it.copy(
                hideKeyboard = false
            )
        }
    }


}