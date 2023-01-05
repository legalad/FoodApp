package com.example.foodapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.data.Ingredient
import com.example.foodapp.model.PantryItemUiState
import com.example.foodapp.model.Units
import java.util.*

@Composable
fun CheckoutScreen(){
}

@Composable
fun PantryItem(
    item: PantryItemUiState,
    ingredient: Ingredient,
    onItemClicked: (PantryItemUiState) -> Unit,
    onAddIconClicked: (PantryItemUiState) -> Unit,
    onEditIconClicked: (PantryItemUiState) -> Unit,
    onDeleteIconClicked: (PantryItemUiState) -> Unit,
    onInputProductNameValueChange: (PantryItemUiState, String) -> Unit,
    onSliderValueChange: (PantryItemUiState, Float) -> Unit,
    onUpdateIconClicked: (PantryItemUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    if (item.isPantryEdited) EditablePantryItem(
        item = item,
        ingredient = ingredient,
        onItemClicked = {},
        onAddIconClicked = onAddIconClicked,
        onEditIconClicked = onEditIconClicked,
        onDeleteIconClicked = onDeleteIconClicked,
        onInputProductNameValueChange = onInputProductNameValueChange,
        onSliderValueChange = onSliderValueChange,
        onUpdateIconClicked = onUpdateIconClicked,
        modifier = modifier
    )
    else {
        if (item.isPantryCollapsed) CollapsedPantryItem(
            item = item,
            onItemClicked = onItemClicked,
            onAddIconClicked = onAddIconClicked,
            onEditIconClicked = onEditIconClicked,
            onDeleteIconClicked = onDeleteIconClicked,
            modifier = modifier
        )
        else ExpandedPantryItem(
            item = item,
            ingredient = ingredient,
            onItemClicked = onItemClicked,
            onAddIconClicked = onAddIconClicked,
            onEditIconClicked = onEditIconClicked,
            onDeleteIconClicked = onDeleteIconClicked,
            modifier = modifier
        )
    }
}

@Composable
fun CollapsedPantryItem(
    item: PantryItemUiState,
    onItemClicked: (PantryItemUiState) -> Unit,
    onAddIconClicked: (PantryItemUiState) -> Unit,
    onEditIconClicked: (PantryItemUiState) -> Unit,
    onDeleteIconClicked: (PantryItemUiState) -> Unit,
    modifier: Modifier = Modifier
    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClicked(item) },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.pantry.item_name,
            style = MaterialTheme.typography.titleMedium
        )
        Row(modifier = Modifier) {
            IconButton(onClick = { onAddIconClicked(item) }) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = ""
                )
            }
            IconButton(onClick = { onEditIconClicked(item) }) {
                Icon(
                    imageVector = Icons.Rounded.Edit,
                    contentDescription = ""
                )
            }
            IconButton(onClick = { onDeleteIconClicked(item) }) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun ExpandedPantryItem(
    item: PantryItemUiState,
    ingredient: Ingredient,
    onItemClicked: (PantryItemUiState) -> Unit,
    onAddIconClicked: (PantryItemUiState) -> Unit,
    onEditIconClicked: (PantryItemUiState) -> Unit,
    onDeleteIconClicked: (PantryItemUiState) -> Unit,
    modifier: Modifier = Modifier
    ) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClicked(item) },
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = ingredient.ingredient_name, style = MaterialTheme.typography.labelLarge)
            Row(modifier = Modifier) {
                IconButton(onClick = { onAddIconClicked(item) }) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = ""
                    )
                }
                IconButton(onClick = { onEditIconClicked(item) }) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = ""
                    )
                }
                IconButton(onClick = { onDeleteIconClicked(item) }) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = ""
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = item.pantry.item_name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = item.pantry.quantity.toString() + " " + item.pantry.unit,
                    style = MaterialTheme.typography.labelSmall
                )
                Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                Row(modifier = Modifier) {
                    Text(
                        text = "Placed date:",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(text = item.pantry.place_date.toString(), modifier = Modifier.weight(3f))
                }

                Row(modifier = Modifier) {
                    Text(
                        text = "Expire date:",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(text = item.pantry.expire_date.toString(), modifier = Modifier.weight(3f))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditablePantryItem(
    item: PantryItemUiState,
    ingredient: Ingredient,
    onItemClicked: (PantryItemUiState) -> Unit,
    onAddIconClicked: (PantryItemUiState) -> Unit,
    onEditIconClicked: (PantryItemUiState) -> Unit,
    onDeleteIconClicked: (PantryItemUiState) -> Unit,
    onInputProductNameValueChange: (PantryItemUiState, String) -> Unit,
    onSliderValueChange: (PantryItemUiState, Float) -> Unit,
    onUpdateIconClicked: (PantryItemUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    val options = Units.values()
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0].name) }
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ExpandedPantryItem(
            item = item,
            ingredient = ingredient,
            onAddIconClicked = onAddIconClicked,
            onEditIconClicked = onEditIconClicked,
            onItemClicked = onItemClicked,
            onDeleteIconClicked = onDeleteIconClicked
        )
        Column(modifier = Modifier.padding(10.dp)) {
            TextField(
                value = item.inputProductName,
                label = { Text(text = "Product name") },
                onValueChange = { onInputProductNameValueChange(item, it) },
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .fillMaxWidth()
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = "MM/DD/YYYY",
                    label = { Text(text = "Placed date") },
                    onValueChange = {},
                    modifier = Modifier
                        .width(140.dp)
                        .padding(bottom = 5.dp, end = 10.dp)
                        .weight(1f)
                )
                TextField(
                    value = "MM/DD/YYYY",
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
            Text(
                text = item.sliderPosition.toString(), modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            LazyRow(modifier = Modifier.height(100.dp)) {
                item {
                    Column(modifier = Modifier) {
                        Slider(
                            value = item.sliderPosition,
                            onValueChange = { onSliderValueChange(item, it) },
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
            IconButton(onClick = { onUpdateIconClicked(item) }, modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)) {
                Icon(imageVector = Icons.Rounded.Done, contentDescription = "")
            }
        }
    }
}




@Preview
@Composable
fun CheckoutScreenPrev() {
    CheckoutScreen()
}

