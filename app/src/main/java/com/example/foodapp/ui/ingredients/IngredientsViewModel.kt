package com.example.foodapp.ui.ingredients

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.source.IngredientRepository
import com.example.foodapp.ui.FoodAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientsViewModel @Inject constructor(
    private val ingredientRepository: IngredientRepository
) : FoodAppViewModel() {
    private val _ingredientsUiState = MutableStateFlow(IngredientsUiState())
    val ingredientsUiState: StateFlow<IngredientsUiState> = _ingredientsUiState
    private lateinit var ingredient: List<Ingredient>

    init {
        viewModelScope.launch {
            ingredient = ingredientRepository.getIngredients()
        }
    }


    fun changeSelectedTab(selectedTab: IngredientTypes) {
        _ingredientsUiState.update {
            it.copy(selectedType = selectedTab)
        }
    }

    fun onSearchTextFieldValueChange (input: String) {
        _ingredientsUiState.update {
            it.copy(
                input = input
            )
        }
        Log.d("DANE", _ingredientsUiState.value.selectedType.name)
    }

    fun onCancelClicked() {
        _ingredientsUiState.update {

            it.copy(
                input = it.input.drop(it.input.length)
            )
        }
    }

    fun hideKeyboard() {
        _ingredientsUiState.update {
            it.copy(
                hideKeyboard = true
            )
        }
    }

    fun onFocusClear() {
        _ingredientsUiState.update {
            it.copy(
                hideKeyboard = false
            )
        }
    }


}