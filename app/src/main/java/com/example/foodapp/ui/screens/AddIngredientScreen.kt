package com.example.foodapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.model.emuns.*
import com.example.foodapp.ui.components.SearchTextField


enum class IngredientTypes (
    @DrawableRes val iconId: Int,
    val names: IngredientSpecificType
    ) {
    VEGETABLES(
        R.drawable.icons8_group_of_vegetables_50,
        Vegetables.POTATO),
    FRUITS(R.drawable.icons8_group_of_fruits_50, Fruits.APPLE),
    MEATS(R.drawable.icons8_steak_50, Meats.PORK),
    DAIRY(R.drawable.icons8_milk_bottle_50, Dairy.YOGHURT),
    GRAIN(R.drawable.icons8_soy_50, Grain.BARLEY),
    FISHES(R.drawable.icons8_fish_food_50, Fishes.SALMON),
    SEAFOOD(R.drawable.icons8_prawn_50, Seafood.SHRIMP),
    SPICES(R.drawable.icons8_black_pepper_50, Spices.PEPPER),
    DRINKS(R.drawable.icons8_cola_50, Drinks.WATER),
    CANDIES(R.drawable.icons8_dessert_50, Candies.DONUT)

}

@Composable
fun IngredientTypeTabRow() {
    var selectedItem by remember { mutableStateOf(0)}
    ScrollableTabRow(selectedTabIndex = selectedItem) {
        IngredientTypes.values().forEachIndexed { index, ingredientTypesItem ->
            Tab(selected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = { Icon(painter = painterResource(id = ingredientTypesItem.iconId), contentDescription = "", tint = Color.Unspecified)},
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
fun AddIngredientScreen(modifier: Modifier = Modifier) {
    var selectedTab by rememberSaveable { mutableStateOf(IngredientTypes.VEGETABLES) }
    var input by rememberSaveable { mutableStateOf("")}
    var hideKeyboard by rememberSaveable{ mutableStateOf(false) }


    Column (modifier = modifier.fillMaxSize().clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { hideKeyboard = true }) {
        SearchTextField(
            onStartValue = input,
            onValueChange = {
                input = it
                            },
            modifier = Modifier.fillMaxWidth(),
            label = {Text(text = "Add ingredient")},
            hideKeyboard = hideKeyboard,
            onFocusClear = { hideKeyboard = false },
            onCancelClicked = {
                input = input.drop(input.length)
            }
        )
        var selectedItem by remember { mutableStateOf(0)}
        ScrollableTabRow(selectedTabIndex = selectedItem) {
            IngredientTypes.values().forEachIndexed { index, ingredientTypesItem ->
                Tab(selected = selectedItem == index,
                    onClick = { selectedItem = index
                                selectedTab = ingredientTypesItem
                              },
                    icon = { Icon(painter = painterResource(id = ingredientTypesItem.iconId), contentDescription = "", tint = Color.Unspecified)},
                    text = { Text(text = ingredientTypesItem.name) })
            }
        }
        LazyColumn (modifier = Modifier.fillMaxWidth()){
            items(selectedTab.names.getValues()) {
                item -> IngredientItem(item, modifier = Modifier.fillMaxWidth())
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
    AddIngredientScreen()
}
