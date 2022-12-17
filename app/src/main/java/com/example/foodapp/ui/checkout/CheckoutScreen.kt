package com.example.foodapp.ui.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.data.Ingredient
import com.example.foodapp.data.Pantry
import com.example.foodapp.model.Units
import java.util.*

@Composable
fun CheckoutScreen(){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditablePantryItem(item: Pantry, ingredient: Ingredient, onItemClicked: () -> Unit, onEditIconClicked: () -> Unit) {
    val options = Units.values()
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0].name) }
    var sliderPosition by remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ExpandedPantryItem(item = item, ingredient = ingredient, onEditIconClicked = onEditIconClicked, onItemClicked = onItemClicked)
        Column(modifier = Modifier.padding(10.dp)) {
            TextField(
                value = "podaj nazwe",
                label = { Text(text = "Product name") },
                onValueChange = {},
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .fillMaxWidth()
            )
            Row (modifier = Modifier.fillMaxWidth()){
                TextField(value = "MM/DD/YYYY",
                    label = { Text(text = "Placed date") },
                    onValueChange = {},
                    modifier = Modifier
                        .width(140.dp)
                        .padding(bottom = 5.dp, end = 10.dp)
                        .weight(1f)
                )
                TextField(value = "MM/DD/YYYY",
                    label = { Text(text = "Expire date") },
                    onValueChange = {},
                    modifier = Modifier
                        .width(140.dp)
                        .padding(bottom = 5.dp)
                        .weight(1f)
                )
            }
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                TextField(
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = { },
                    label = { Text("Unit") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionText = selectionOption.name
                                expanded = false
                            },
                            text = { Text(text = selectionOption.name) }
                        )
                    }
                }
            }
            Text(text = sliderPosition.toString(), modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally))
            LazyRow(modifier = Modifier.height(100.dp)) {
                item {
                    Column(modifier = Modifier) {
                        Slider(
                            value = sliderPosition,
                            onValueChange = { sliderPosition = it },
                            valueRange = 0f..50f,
                            onValueChangeFinished = {
                                // launch some business logic update with the state you hold
                                // viewModel.updateSelectedSliderValue(sliderPosition)
                            },
                            modifier = Modifier.width(10000.dp),
                            steps = 499,
                            colors = SliderDefaults.colors(
                                thumbColor = MaterialTheme.colorScheme.secondary,
                                activeTrackColor = MaterialTheme.colorScheme.secondary
                            )
                        )
                        Row(
                            modifier = Modifier
                                .width(1000.dp)
                                .padding(start = 7.dp)
                        ) {
                            Text(text = "0", modifier = Modifier.width(178.dp))
                            Text(text = "1", modifier = Modifier.width(178.dp))
                            Text(text = "2", modifier = Modifier.width(178.dp))
                            Text(text = "3", modifier = Modifier.width(178.dp))
                            Text(text = "4", modifier = Modifier.width(178.dp))
                            Text(text = "5", modifier = Modifier.width(178.dp))
                            Text(text = "6", modifier = Modifier.width(178.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExpandedPantryItem(item: Pantry, ingredient: Ingredient, onItemClicked: () -> Unit, onEditIconClicked: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable(
            onClick = onItemClicked,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        )) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(text = ingredient.name, style = MaterialTheme.typography.labelLarge)
            Row(modifier = Modifier) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = ""
                    )
                }
                IconButton(onClick = onEditIconClicked) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = ""
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = ""
                    )
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.weight(5f)) {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Text(text = item.quantity.toString() + " " + item.unit, style = MaterialTheme.typography.labelSmall)
                Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                Row(modifier = Modifier) {
                    Text(text = "Placed date:", modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleSmall)
                    Text(text = item.place_date.toString(), modifier = Modifier.weight(2f))
                }

                Row(modifier = Modifier) {
                    Text(text = "Expire date:", modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleSmall)
                    Text(text = item.expire_date.toString(), modifier = Modifier.weight(2f))
                }
            }
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(5.dp)) {

            }
        }
    }
}

@Composable
fun PantryItem(item: Pantry, ingredient: Ingredient) {
    var isPantryCollapsed by remember{(mutableStateOf(true))}
    var isPantryEdited by remember{ mutableStateOf(false)}
    if (isPantryEdited) EditablePantryItem(item, ingredient, onItemClicked = {}, onEditIconClicked = {isPantryEdited = !isPantryEdited})
    else {
        if (isPantryCollapsed) CollapsedPantryItem(item = item, onItemClicked = {isPantryCollapsed = !isPantryCollapsed}, onEditIconClicked = {isPantryEdited = !isPantryEdited})
        else ExpandedPantryItem(item = item, ingredient = ingredient, onItemClicked = {isPantryCollapsed = !isPantryCollapsed}, onEditIconClicked = {isPantryEdited = !isPantryEdited})
    }
}

@Composable
fun CollapsedPantryItem(item: Pantry, onItemClicked: () -> Unit, onEditIconClicked: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clickable(onClick = onItemClicked,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ),
        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = item.name, style = MaterialTheme.typography.titleMedium)
        Row(modifier = Modifier) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = ""
                )
            }
            IconButton(onClick = onEditIconClicked) {
                Icon(
                    imageVector = Icons.Rounded.Edit,
                    contentDescription = ""
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = ""
                )
            }
        }
    }
}


@Preview
@Composable
fun CheckoutScreenPrev() {
    CheckoutScreen()
}

@Preview (showBackground = true)
@Composable
fun CheckoutItemExpandedPrev() {
    EditablePantryItem(item = Pantry(1, "Złociste jabłko", "", Calendar.getInstance().time, Calendar.getInstance().time, 0.6f, "KG", 1), ingredient = Ingredient(1, "Apple", "", "", ""), {}, {})
}

@Preview (showBackground = true)
@Composable
fun CheckoutItemCollapsedPrev() {
    Column(modifier = Modifier.padding(10.dp)) {

    }
}

@Preview (showBackground = true)
@Composable
fun CollapsedPantryItemPrev() {
    Column(modifier = Modifier) {

    }
}

@Preview (showBackground = true)
@Composable
fun PantryItemPrev() {
    LazyColumn(modifier = Modifier) {
        item  {
            PantryItem(
                item = Pantry(
                    1,
                    "Pomarańcza",
                    "",
                    Calendar.getInstance().time,
                    Calendar.getInstance().time,
                    1f,
                    "PSC",
                    1
                ), ingredient = Ingredient(1, "Orange", "", "", "")
            )
        }
        item {
            PantryItem(
                item = Pantry(
                    1,
                    "Złociste jabłko",
                    "",
                    Calendar.getInstance().time,
                    Calendar.getInstance().time,
                    0.6f,
                    "KG",
                    1
                ), ingredient = Ingredient(1, "Apple", "", "", "")
            )
        }
        item {
            PantryItem(
                item = Pantry(
                    1,
                    "Krewetki - Lidl",
                    "",
                    Calendar.getInstance().time,
                    Calendar.getInstance().time,
                    1.2f,
                    "KG",
                    1
                ), ingredient = Ingredient(1, "Shrimp", "", "", "")
            )
        }
    }
}
