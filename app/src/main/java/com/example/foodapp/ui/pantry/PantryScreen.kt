package com.example.foodapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.ui.components.SearchTextField
import com.example.foodapp.ui.ingredients.IngredientTypes

@Composable
fun PantryScreen() {
    Column (modifier = Modifier.fillMaxSize()){
        SearchTextField(
            onStartValue = "test",
            onValueChange = {},
            label = { Text(text = "Search ingredient")},
            modifier = Modifier.fillMaxWidth(), onCancelClicked = {})
        Pantry()
    }
}

@Composable
fun Pantry() {
    Column (modifier = Modifier.padding(20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.icons8_fridge_96),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )
                Column {
                    Text(text = "Pantry")
                    Text(text = "Pantry details", style = MaterialTheme.typography.bodySmall)
                }
            }
            Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "")
        }
        Divider()
        LazyColumn {
            item {
                PantryType(IngredientTypes.FRUITS, true)
            }
            item {
                PantryType(ingredientTypes = IngredientTypes.CANDIES, true)
            }
            item {
                PantryType(ingredientTypes = IngredientTypes.DAIRY, false)
            }
            item {
                PantryType(ingredientTypes = IngredientTypes.FISHES, true)
            }
            item {
                PantryType(ingredientTypes = IngredientTypes.MEATS, true)
            }
            item {
                PantryType(ingredientTypes = IngredientTypes.SPICES, false)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantryType (ingredientTypes: IngredientTypes, isExpanded: Boolean) {
        Column {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(text = ingredientTypes.name)
                Icon(painter = painterResource(id = R.drawable.ic_round_expand_more_24), contentDescription = "")
            }
            if (isExpanded) {PantryTypeCollapsed()}
            else {
                PantryTypeExpanded()}
            Divider()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PantryTypeCollapsed() {
    LazyRow(verticalAlignment = Alignment.Bottom) {
        item {
            ElevatedChipPantryItem()
        }
        item {
            ElevatedAssistChip(onClick = { /*TODO*/ }, label = { Text(text = "") }, leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.icons8_group_of_fruits_50),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            })
        }
        item {
            ElevatedCardPantryItem()
        }
        items(6) {
            ElevatedCardPantryItem()
        }
    }
}


@Composable
fun PantryTypeExpanded() {
    Column {
        PantryTypeExpandedListItem()
        PantryTypeExpandedListItem()
        PantryTypeExpandedListItem()
        PantryTypeExpandedListItem()
        PantryTypeExpandedListItem()
        PantryTypeExpandedListItem()
    }
}

@Composable
fun PantryTypeExpandedListItem() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(5.dp)) {
        Text(text = "item")
        Row {
            Icon(imageVector = Icons.Rounded.Edit, contentDescription = "Edit pantry item")
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete pantry item")
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PantryTypeExpandedListItemPrev() {
    PantryTypeExpandedListItem()
}


@Preview (showBackground = true)
@Composable
fun PantryScreenPrev() {
    PantryScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview (showBackground = true)
@Composable
fun PantryItemPrev() {
    ElevatedCardPantryItem()
}

@Preview (showBackground = true)
@Composable
fun ElevatedChipPantryItemPrev() {
    ElevatedChipPantryItem()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElevatedCardPantryItem() {
    BadgedBox(
        badge = {
            Badge (modifier = Modifier.padding(0.dp)) {
                val badgeNumber = "8"
                Text(
                    badgeNumber,
                    modifier = Modifier.semantics {
                        contentDescription = "$badgeNumber new notifications"
                    }
                )
            }
        }, modifier = Modifier
            .padding(top = 15.dp, end = 15.dp)
    ) {
        ElevatedCard(onClick = { /*TODO*/ }, modifier = Modifier
            .padding(bottom = 8.dp)){
            Icon(
                painter = painterResource(id = R.drawable.icons8_group_of_fruits_50),
                contentDescription = "",
                tint = Color.Unspecified, modifier = Modifier
                    .padding(5.dp)
                    .size(40.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElevatedChipPantryItem() {
    BadgedBox(
        badge = {
            Badge (modifier = Modifier.padding(0.dp)) {
                val badgeNumber = "8"
                Text(
                    badgeNumber,
                    modifier = Modifier.semantics {
                        contentDescription = "$badgeNumber new notifications"
                    }
                )
            }
        }, modifier = Modifier
            .padding(top = 15.dp, end = 15.dp)
    ) {
        ElevatedAssistChip(onClick = { /*TODO*/ }, { Text(text = "jabłko")})
    }
}
