package com.example.foodapp.ui.ingredients

import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.source.IngredientRepository
import com.example.foodapp.data.utils.Mappers
import com.example.foodapp.model.IngredientUiState
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

    init {
        viewModelScope.launch {
            _ingredientsUiState.update { it ->
                it.copy(ingredientList = ingredientRepository.getIngredients().map { Mappers.fromIngredientToIngredientUiState(it) })
            }
        }
    }

    fun getFilteredIngredientsList(): List<IngredientUiState> {
        return if (_ingredientsUiState.value.input.length >=3)
            _ingredientsUiState.value.ingredientList.filter {
                it.ingredient.name.uppercase().contains(_ingredientsUiState.value.input.uppercase().trim())
            }
        else filterIngredientList (_ingredientsUiState.value.selectedType.filter) //ingredientsUiState.value.ingredientList.filter { it.group == "Fruits" }
    }

    private fun filterIngredientList(f: (List<IngredientUiState>) -> List<IngredientUiState>): List<IngredientUiState>{
        return f(_ingredientsUiState.value.ingredientList)
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
    }

    fun onCancelClicked() {
        _ingredientsUiState.update {

            it.copy(
                input = it.input.drop(it.input.length)
            )
        }
    }

    fun onCheckedChange(ingredientUiState: IngredientUiState){
        val temp = _ingredientsUiState.value.ingredientList
        val index = temp.indexOf(ingredientUiState)
        val force = _ingredientsUiState.value.forceRecomposition

        if (index != -1){
            temp[index].selected = !temp[index].selected
            _ingredientsUiState.update {
                it.copy(
                    ingredientList = temp,
                    forceRecomposition = !force
                )
            }
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