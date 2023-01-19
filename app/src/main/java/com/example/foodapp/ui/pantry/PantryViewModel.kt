package com.example.foodapp.ui.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.source.PantryRepository
import com.example.foodapp.data.utils.toPantryItemUiStateList
import com.example.foodapp.model.PantryItemOperations
import com.example.foodapp.model.PantryItemUiState
import com.example.foodapp.ui.ingredients.IngredientTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.foodapp.data.Result

@HiltViewModel
class PantryViewModel @Inject constructor(
    private val pantryRepository: PantryRepository
): ViewModel(), PantryItemOperations {
    private val _pantryUiState = MutableStateFlow(PantryUiState())
    val pantryUiState: StateFlow<PantryUiState> = _pantryUiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = pantryRepository.getPantryItems().first
            when (result){
                is Result.Success -> {
                    _pantryUiState.update {
                        it.copy(
                            pantryItemsList = result.data.toPantryItemUiStateList()
                        )
                    }
                }
                is Result.Warning -> {
                    _pantryUiState.update {
                        it.copy(
                            pantryItemsList = result.data.toPantryItemUiStateList()
                        )
                    }
                }
                is Result.Error -> ""//TODO add later screen state
            }

        }
    }

    fun getFilteredPantryItemList(type: IngredientTypes): List<PantryItemUiState> {
        return filterIngredientList (type.filterPantry)
    }

    private fun filterIngredientList(f: (List<PantryItemUiState>) -> List<PantryItemUiState>): List<PantryItemUiState>{
        return f(_pantryUiState.value.pantryItemsList)
    }

    override fun onItemButtonClicked(item: PantryItemUiState) {
        val tmpMap = getPantryItemList()
        val tmpItem = item.copy(isPantryCollapsed = !item.isPantryCollapsed)
        updatePantryItemState(tmpMap, item, tmpItem)
    }

    override fun onAddItemButtonClicked(item: PantryItemUiState) {
        TODO("Not yet implemented")
    }

    override fun onEditItemButtonClicked(item: PantryItemUiState) {
        val tmpMap = getPantryItemList()
        val tmpItem = item.copy(isPantryEdited = !item.isPantryEdited)
        updatePantryItemState(tmpMap, item, tmpItem)
    }

    override fun onDeleteItemButtonClicked(item: PantryItemUiState) {
        viewModelScope.launch(Dispatchers.IO) {
            pantryRepository.deletePantryItem(item.pantryItem)
        }
    }

    override fun onInputProductNameValueChange(item: PantryItemUiState, value: String) {
        val tmpList = getPantryItemList()
        val tmpItem = item.copy(inputProductName = value)
        updatePantryItemState(tmpList, item, tmpItem)
    }

    override fun onSliderValueChange(item: PantryItemUiState, value: Float) {
        val tmpList: MutableList<PantryItemUiState> = getPantryItemList()
        val tmpItem = item.copy(sliderPosition = value)
        updatePantryItemState(tmpList, item, tmpItem)
    }

    override fun onUpdateIconClicked(item: PantryItemUiState) {

        val tmpList = getPantryItemList()
        val tmpItem = item.copy(isPantryEdited = false, isPantryCollapsed = false ,pantryItem = item.pantryItem.copy(name = item.inputProductName, unit = item.selectedOptionText, quantity = item.sliderPosition))
        viewModelScope.launch(Dispatchers.IO) {
            pantryRepository.updatePantryItem(tmpItem.pantryItem)
        }
        updatePantryItemState(tmpList, item, tmpItem)
    }

    private fun getPantryItemList(): MutableList<PantryItemUiState> {
        return _pantryUiState.value.pantryItemsList.toMutableList()
    }

    private fun updatePantryItemState(
        tmpList: MutableList<PantryItemUiState>,
        item: PantryItemUiState,
        tmpItem: PantryItemUiState
    ) {
        val index =  tmpList.indexOf(item)
        tmpList[index] = tmpItem

        //sort?

        updatePantryItemsList(tmpList)
    }

    private fun updatePantryItemsList(value: List<PantryItemUiState>){
        _pantryUiState.update {
            it.copy(
                pantryItemsList = value
            )
        }
    }


}