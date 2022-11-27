package com.example.foodapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.ui.components.SearchTextField


enum class IngredientTypes (@DrawableRes val iconId: Int) {
    VEGETABLES(R.drawable.icons8_group_of_vegetables_50),
    FRUITS(R.drawable.icons8_group_of_fruits_50),
    MEATS(R.drawable.icons8_steak_50),
    DAIRY(R.drawable.icons8_milk_bottle_50),
    GRAIN(R.drawable.icons8_soy_50),
    FISHES(R.drawable.icons8_fish_food_50),
    SEAFOOD(R.drawable.icons8_prawn_50),
    SPICES(R.drawable.icons8_black_pepper_50),
    DRINKS(R.drawable.icons8_cola_50),
    CANDY(R.drawable.icons8_dessert_50)

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
fun IngredientItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(start = 40.dp, end = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Icon(painter = painterResource(id = R.drawable.icons8_soy_50), contentDescription = "")
            Text(text = "NAME")
        }
        Checkbox(checked = true, onCheckedChange = {})
    }
}

@Composable
fun AddIngredientScreen(modifier: Modifier = Modifier) {
    Column (modifier = modifier.fillMaxSize()) {
        SearchTextField(
            onStartValue = "test",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = {Text(text = "Add ingredient")})
        IngredientTypeTabRow()
        LazyColumn (modifier = Modifier.fillMaxWidth()){
            items(20) { ingredient ->
                IngredientItem(modifier = Modifier.fillMaxWidth())
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
