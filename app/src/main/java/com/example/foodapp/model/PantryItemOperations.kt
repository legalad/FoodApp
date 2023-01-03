package com.example.foodapp.model

interface PantryItemOperations {
    fun onItemButtonClicked(item: PantryItemUiState)
    fun onAddItemButtonClicked(item: PantryItemUiState)
    fun onEditItemButtonClicked(item: PantryItemUiState)
    fun onDeleteItemButtonClicked(item: PantryItemUiState)
    fun onInputProductNameValueChange(item: PantryItemUiState, value: String)
    fun onSliderValueChange(item: PantryItemUiState, value: Float)
    fun onUpdateIconClicked(item: PantryItemUiState)
}