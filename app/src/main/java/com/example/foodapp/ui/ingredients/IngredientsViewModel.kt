package com.example.foodapp.ui.ingredients

import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.source.IngredientRepository
import com.example.foodapp.data.utils.*
import com.example.foodapp.model.IngredientUiState
import com.example.foodapp.model.PantryItemOperations
import com.example.foodapp.model.PantryItemUiState
import com.example.foodapp.ui.FoodAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class IngredientsViewModel @Inject constructor(
    private val ingredientRepository: IngredientRepository
) : FoodAppViewModel(), PantryItemOperations {
    private val _ingredientsUiState = MutableStateFlow(IngredientsUiState())
    val ingredientsUiState: StateFlow<IngredientsUiState> = _ingredientsUiState

    init {
        viewModelScope.launch {
            _ingredientsUiState.update { it ->
                it.copy(ingredientList = ingredientRepository.getIngredients().toIngredientUiStateList())
            }
        }

        /*val ingredientList: Deferred<List<IngredientUiState>> = viewModelScope.async {
            ingredientRepository.getIngredients().map { Mappers.fromIngredientToIngredientUiState(it) }
        }
        viewModelScope.launch {
            _ingredientsUiState.update { it ->
                it.copy(ingredientList = ingredientList.await())
            }
        }*/
    }

    fun getFilteredIngredientsList(): List<IngredientUiState> {
        return if (_ingredientsUiState.value.input.length >=3)
            _ingredientsUiState.value.ingredientList.filter {
                it.ingredient.ingredient_name.uppercase().contains(_ingredientsUiState.value.input.uppercase().trim())
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

    //Search text box logic

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

    //Adding generic ingredients logic

    fun onCheckedChange(ingredientUiState: IngredientUiState){
        val tmp = _ingredientsUiState.value.ingredientList.toMutableList()
        val index = tmp.indexOf(ingredientUiState)
        tmp[index] = tmp[index].copy(selected = !tmp[index].selected)
        if (index != -1){
            _ingredientsUiState.update {
                it.copy(
                    ingredientList = tmp,
                )
            }
        }
    }

    fun onAddFabClicked(){
        val tmp = _ingredientsUiState.value.ingredientList.filter { it.selected }.map { (it.ingredient.toPantryItem().toPantryItemUiState()) }
        _ingredientsUiState.update {
            it.copy(addingScreen = true, pantryItemList = tmp)
        }
    }

    fun onAddToPantryFabClicked(){
        viewModelScope.launch {
            ingredientRepository.addPantryItemList(_ingredientsUiState.value.pantryItemList.toPantryItemList())
        }
        _ingredientsUiState.update {
            it.copy(addingScreen = false)
        }
    }

    //Item logic (operation on items)

    override fun onItemButtonClicked(item: PantryItemUiState){
        val tmp = _ingredientsUiState.value.pantryItemList.toMutableList()
        val index = tmp.indexOf(item)
        tmp[index] = tmp[index].copy(isPantryCollapsed = !tmp[index].isPantryCollapsed)
        _ingredientsUiState.update {
            it.copy(
                pantryItemList = tmp,
            )
        }
    }

    override fun onAddItemButtonClicked(item: PantryItemUiState){
        val tmp = _ingredientsUiState.value.pantryItemList.toMutableList()
        val index = tmp.indexOf(item) + 1
        tmp.add(index, item)
        _ingredientsUiState.update {
            it.copy(
                pantryItemList = tmp
            )
        }

    }

    override fun onEditItemButtonClicked(item: PantryItemUiState){
        val tmp = _ingredientsUiState.value.pantryItemList.toMutableList()
        val index = tmp.indexOf(item)
        tmp[index] = tmp[index].copy(isPantryEdited = !tmp[index].isPantryEdited)
        _ingredientsUiState.update {
            it.copy(
                pantryItemList = tmp
            )
        }
    }

    override fun onDeleteItemButtonClicked(item: PantryItemUiState){
        val tmp = _ingredientsUiState.value.pantryItemList.toMutableList()
        val tmp2 = _ingredientsUiState.value.ingredientList.toMutableList()
        val index = tmp.indexOf(item)
        val ingredientId = tmp[index].pantryItem.id
        tmp.remove(item)
        tmp2.find { it.ingredient.ingredient_id == ingredientId }?.let { onCheckedChange(it) }
        _ingredientsUiState.update {
            it.copy(
                pantryItemList = tmp
            )
        }
    }


    override fun onInputProductNameValueChange(item: PantryItemUiState, value: String){
        val tmp = _ingredientsUiState.value.pantryItemList.toMutableList()
        val index = tmp.indexOf(item)
        tmp[index] = tmp[index].copy(inputProductName = value)
        _ingredientsUiState.update {
            it.copy(
                pantryItemList = tmp,
            )
        }
    }

    override fun onSliderValueChange(item: PantryItemUiState, value: Float){
        val tmp = _ingredientsUiState.value.pantryItemList.toMutableList()
        val index = tmp.indexOf(item)
        tmp[index] = tmp[index].copy(sliderPosition = value)
        _ingredientsUiState.update {
            it.copy(
                pantryItemList = tmp,
            )
        }
    }

    override fun onUpdateIconClicked(item: PantryItemUiState) {

    }

    fun onBackedPressed() {
        _ingredientsUiState.update {
            it.copy(
                addingScreen = false
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