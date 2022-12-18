package com.example.foodapp.ui.ingredients

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodapp.R
import com.example.foodapp.data.Ingredient
import com.example.foodapp.model.IngredientUiState
import com.example.foodapp.model.PantryItemUiState
import com.example.foodapp.ui.checkout.PantryItem
import com.example.foodapp.ui.components.SearchTextField


enum class IngredientTypes(
    @DrawableRes val iconId: Int,
    val filter: (List<IngredientUiState>) -> List<IngredientUiState>
) {
    VEGETABLES(
        R.drawable.icons8_group_of_vegetables_50,
        { it -> it.filter { it.ingredient.group == "Vegetables" } }),
    FRUITS(
        R.drawable.icons8_group_of_fruits_50,
        { it -> it.filter { it.ingredient.group == "Fruits" } }),
    MEATS(
        R.drawable.icons8_steak_50,
        { it -> it.filter { it.ingredient.group == "Animal foods" } }),
    DAIRY(
        R.drawable.icons8_milk_bottle_50,
        { it -> it.filter { it.ingredient.group == "Milk and milk products" } }),
    GRAIN(
        R.drawable.icons8_soy_50,
        { it -> it.filter { it.ingredient.group == "Cereals and cereal products" } }),
    FISHES(
        R.drawable.icons8_fish_food_50,
        { it -> it.filter { it.ingredient.subGroup == "Fishes" } }),
    SEAFOOD(
        R.drawable.icons8_prawn_50,
        { it -> it.filter { it.ingredient.group == "Aquatic foods" && it.ingredient.subGroup != "Fishes" } }),
    SPICES(
        R.drawable.icons8_black_pepper_50,
        { it -> it.filter { it.ingredient.group == "Herbs and Spices" } }),
    DRINKS(R.drawable.icons8_cola_50, { it -> it.filter { it.ingredient.group == "Beverages" } }),
    CANDIES(
        R.drawable.icons8_dessert_50,
        { it -> it.filter { it.ingredient.group == "Confectioneries" } })

}

@Composable
fun IngredientTypeTabRow() {
    var selectedItem by remember { mutableStateOf(0) }
    ScrollableTabRow(selectedTabIndex = selectedItem) {
        IngredientTypes.values().forEachIndexed { index, ingredientTypesItem ->
            Tab(selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = {
                    Icon(
                        painter = painterResource(id = ingredientTypesItem.iconId),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                },
                text = { Text(text = ingredientTypesItem.name) })
        }
    }
}

@Composable
fun IngredientItem(
    ingredientUiState: IngredientUiState,
    onCheckedChange: (IngredientUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(start = 40.dp, end = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = ingredientUiState.ingredient.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(8f)
        )
        Checkbox(checked = ingredientUiState.selected, onCheckedChange = {
            onCheckedChange(ingredientUiState)
        }, modifier = Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientsScreen(viewModel: IngredientsViewModel, modifier: Modifier = Modifier) {

    //consider to move this up to AddIngredientRoute(viewModel) and in only passed uiState to AddIngredientScreen with func
    val uiState by viewModel.ingredientsUiState.collectAsState()

    if (!uiState.addingScreen) {
        Scaffold(
            floatingActionButton = {
                SmallFloatingActionButton(onClick = { viewModel.onAddFabClicked() }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            content = { innerPadding ->
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = viewModel::hideKeyboard
                        )
                        .padding(innerPadding)
                ) {
                    SearchTextField(
                        onStartValue = uiState.input,
                        onValueChange = viewModel::onSearchTextFieldValueChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Add ingredient") },
                        hideKeyboard = uiState.hideKeyboard,
                        onFocusClear = viewModel::onFocusClear,
                        onCancelClicked = viewModel::onCancelClicked
                    )
                    ScrollableTabRow(
                        selectedTabIndex = uiState.selectedType.ordinal
                    ) {
                        IngredientTypes.values().forEachIndexed { index, ingredientTypesItem ->
                            Tab(
                                selected = uiState.selectedType.ordinal == index,
                                onClick = { viewModel.changeSelectedTab(ingredientTypesItem) },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = ingredientTypesItem.iconId),
                                        contentDescription = "",
                                        tint = Color.Unspecified
                                    )
                                },
                                text = { Text(text = ingredientTypesItem.name) })
                        }
                    }
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        //model that logic to viewModel later
                        val ingredients = viewModel.getFilteredIngredientsList()
                        //if (uiState.input.length >= 3) Datasource.ingredientList.filter { it.name.uppercase().contains(uiState.input.uppercase()) } else uiState.selectedType.ingredients
                        items(ingredients) { item ->
                            IngredientItem(
                                item,
                                onCheckedChange = viewModel::onCheckedChange,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            })
    } else AddPantryItemsScreen(
        pantryItems = uiState.pantryItemList,
        onItemClicked = viewModel::onItemButtonClicked,
        onEditIconClicked = viewModel::onEditItemButtonClicked,
        onInputProductNameValueChange = viewModel::onInputProductNameValueChange,
        onSliderValueChange = viewModel::onSliderValueChange
    )
}

@Composable
fun AddPantryItemsScreen(
    pantryItems: List<PantryItemUiState>,
    onItemClicked: (PantryItemUiState) -> Unit,
    onEditIconClicked: (PantryItemUiState) -> Unit,
    onInputProductNameValueChange: (PantryItemUiState, String) -> Unit,
    onSliderValueChange: (PantryItemUiState, Float) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(pantryItems) { item ->
            Card(modifier = Modifier.padding(5.dp)) {
                PantryItem(
                    item = item,
                    ingredient = Ingredient(0, "Apple", "Ap", "Ap", "Ap"),
                    onItemClicked = onItemClicked,
                    onEditIconClicked = onEditIconClicked,
                    onInputProductNameValueChange = onInputProductNameValueChange,
                    onSliderValueChange = onSliderValueChange
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestPrev() {
    IngredientTypeTabRow()
}


@Preview(showBackground = true)
@Composable
fun TestPrev2() {
    IngredientsScreen(viewModel())
}