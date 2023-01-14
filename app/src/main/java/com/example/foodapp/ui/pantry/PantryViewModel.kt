package com.example.foodapp.ui.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.data.source.PantryRepository
import com.example.foodapp.data.utils.Mappers
import com.example.foodapp.model.PantryItemOperations
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
): ViewModel(), PantryItemOperations {
    private val _pantryUiState = MutableStateFlow(PantryUiState())
    val pantryUiState: StateFlow<PantryUiState> = _pantryUiState

    init {
        viewModelScope.launch {
            val pantryItemsMapWithState: MutableMap<PantryItemUiState, IngredientEntity> = mutableMapOf()
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

    fun getFilteredPantryItemList(type: IngredientTypes): Map<PantryItemUiState, IngredientEntity> {
        return filterIngredientList (type.filterPantry)
    }

    private fun filterIngredientList(f: (Map<PantryItemUiState, IngredientEntity>) -> Map<PantryItemUiState, IngredientEntity>): Map<PantryItemUiState, IngredientEntity>{
        return f(_pantryUiState.value.pantryItemsList)
    }

    override fun onItemButtonClicked(item: PantryItemUiState) {
        val tmpMap = getPantryItemMap()
        val tmpItem = item.copy(isPantryCollapsed = !item.isPantryCollapsed)
        updatePantryItemState(tmpMap, item, tmpItem)
    }

    override fun onAddItemButtonClicked(item: PantryItemUiState) {
        TODO("Not yet implemented")
    }

    override fun onEditItemButtonClicked(item: PantryItemUiState) {
        val tmpMap = getPantryItemMap()
        val tmpItem = item.copy(isPantryEdited = !item.isPantryEdited)
        updatePantryItemState(tmpMap, item, tmpItem)
    }

    override fun onDeleteItemButtonClicked(item: PantryItemUiState) {
        viewModelScope.launch {
            pantryRepository.deletePantryItem(item.pantryEntity)
        }
    }

    override fun onInputProductNameValueChange(item: PantryItemUiState, value: String) {
        val tmpMap = getPantryItemMap()
        val tmpItem = item.copy(inputProductName = value)
        updatePantryItemState(tmpMap, item, tmpItem)
    }

    override fun onSliderValueChange(item: PantryItemUiState, value: Float) {
        val tmpMap = getPantryItemMap()
        val tmpItem = item.copy(sliderPosition = value)
        updatePantryItemState(tmpMap, item, tmpItem)
    }

    override fun onUpdateIconClicked(item: PantryItemUiState) {

        val tmpMap = getPantryItemMap()
        val tmpItem = item.copy(isPantryEdited = false, isPantryCollapsed = false ,pantryEntity = item.pantryEntity.copy(item_name = item.inputProductName, unit = item.selectedOptionText, quantity = item.sliderPosition))
        viewModelScope.launch {
            pantryRepository.updatePantryItems(tmpItem.pantryEntity)
        }
        updatePantryItemState(tmpMap, item, tmpItem)
    }

    private fun getPantryItemMap(): MutableMap<PantryItemUiState, IngredientEntity> {
        return _pantryUiState.value.pantryItemsList.toMutableMap()
    }

    private fun updatePantryItemState(
        tmpMap: MutableMap<PantryItemUiState, IngredientEntity>,
        item: PantryItemUiState,
        tmpItem: PantryItemUiState
    ) {
        tmpMap[item]?.let { it ->
            tmpMap[tmpItem] = it
            tmpMap.remove(item)
            val sortedMap: MutableMap<PantryItemUiState, IngredientEntity> = LinkedHashMap()
            tmpMap.entries.sortedBy { it.key.pantryEntity.item_name }.forEach { sortedMap[it.key] = it.value }
            updatePantryItemMap(sortedMap)
        }
    }

    private fun updatePantryItemMap(value: Map<PantryItemUiState, IngredientEntity>){
        _pantryUiState.update {
            it.copy(
                pantryItemsList = value
            )
        }
    }


}