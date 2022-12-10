package com.example.foodapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foodapp.R
import com.example.foodapp.data.Datasource
import com.example.foodapp.model.Ingredient
import com.example.foodapp.model.emuns.*
import com.example.foodapp.ui.components.SearchTextField
import com.example.foodapp.ui.viewModel.AddIngredientViewModel


enum class IngredientTypes(
    @DrawableRes val iconId: Int,
    val names: IngredientSpecificType,
    val ingredients: List<Ingredient>
) {
    VEGETABLES(
        R.drawable.icons8_group_of_vegetables_50,
        Vegetables.POTATO,
        Datasource.ingredientList.filter { it.group == "Vegetables"}
    ),
    FRUITS(R.drawable.icons8_group_of_fruits_50, Fruits.APPLE, Datasource.ingredientList.filter { it.group == "Fruits"}),
    MEATS(R.drawable.icons8_steak_50, Meats.PORK, Datasource.ingredientList.filter { it.group == "Animal foods"}),
    DAIRY(R.drawable.icons8_milk_bottle_50, Dairy.YOGHURT, Datasource.ingredientList.filter { it.group == "Milk and milk products"}),
    GRAIN(R.drawable.icons8_soy_50, Grain.BARLEY, Datasource.ingredientList.filter { it.group == "Cereals and cereal products"}),
    FISHES(R.drawable.icons8_fish_food_50, Fishes.SALMON, Datasource.ingredientList.filter { it.subGroup == "Fishes"}),
    SEAFOOD(R.drawable.icons8_prawn_50, Seafood.SHRIMP, Datasource.ingredientList.filter { it.group == "Aquatic foods" && it.subGroup != "Fishes"}),
    SPICES(R.drawable.icons8_black_pepper_50, Spices.PEPPER, Datasource.ingredientList.filter { it.group == "Herbs and Spices"}),
    DRINKS(R.drawable.icons8_cola_50, Drinks.WATER, Datasource.ingredientList.filter { it.group == "Beverages"}),
    CANDIES(R.drawable.icons8_dessert_50, Candies.DONUT, Datasource.ingredientList.filter { it.group == "Confectioneries"})

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
fun IngredientItem(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(start = 40.dp, end = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            //Icon(painter = painterResource(id = R.drawable.icons8_soy_50), contentDescription = "")
            Text(text = name)
        }
        Checkbox(checked = true, onCheckedChange = {})
    }
}

@Composable
fun AddIngredientScreen(viewModel: AddIngredientViewModel, modifier: Modifier = Modifier) {

    //consider to move this up to AddIngredientRoute(viewModel) and in only passed uiState to AddIngredientScreen with func
    val uiState by viewModel.addIngredientUiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = viewModel::hideKeyboard
            )
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
            val ingredients = if (uiState.input.length >= 3) Datasource.ingredientList.filter { it.name.uppercase().contains(uiState.input.uppercase()) } else uiState.selectedType.ingredients
            items(ingredients) { item ->
                IngredientItem(item.name, modifier = Modifier.fillMaxWidth())
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
    AddIngredientScreen(viewModel())
}
