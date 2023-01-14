package com.example.foodapp.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.data.IngredientEntity
import com.example.foodapp.model.PantryItemUiState
import com.example.foodapp.ui.components.PantryItem
import com.example.foodapp.ui.ingredients.IngredientTypes
import com.example.foodapp.ui.pantry.PantryViewModel


@Composable
fun PantryScreen(viewModel: PantryViewModel) {

    val uiState by viewModel.pantryUiState.collectAsState()

    Column(modifier = Modifier.padding(20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.icons8_fridge_96),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )
                Column {
                    Text(text = "Pantry")
                    Text(
                        text = "Total items: ${uiState.pantryItemsList.size}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "")
        }
        Divider()
        LazyColumn {

            itemsIndexed(IngredientTypes.values()) { index, item ->
                PantryType(
                    ingredientTypes = item,
                    pantryItemsMap = viewModel.getFilteredPantryItemList(item),
                    onItemClicked = viewModel::onItemButtonClicked,
                    onEditIconClicked = viewModel::onEditItemButtonClicked,
                    onDeleteIconClicked = viewModel::onDeleteItemButtonClicked,
                    onInputProductNameValueChange = viewModel::onInputProductNameValueChange,
                    onSliderValueChange = viewModel::onSliderValueChange,
                    onUpdateIconClicked = viewModel::onUpdateIconClicked
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantryType(
    ingredientTypes: IngredientTypes,
    pantryItemsMap: Map<PantryItemUiState, IngredientEntity>,
    onItemClicked: (PantryItemUiState) -> Unit,
    onEditIconClicked: (PantryItemUiState) -> Unit,
    onDeleteIconClicked: (PantryItemUiState) -> Unit,
    onInputProductNameValueChange: (PantryItemUiState, String) -> Unit,
    onSliderValueChange: (PantryItemUiState, Float) -> Unit,
    onUpdateIconClicked: (PantryItemUiState) -> Unit
) {
    var isExpanded by remember { mutableStateOf(true) }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = ingredientTypes.iconId), contentDescription = "", tint = Color.Unspecified)
            Text(text = ingredientTypes.name)
            IconButton(onClick = { isExpanded = !isExpanded }) {
                if (isExpanded) Icon(
                    painter = painterResource(id = R.drawable.ic_round_expand_more_24),
                    contentDescription = ""
                )
                else Icon(
                    painter = painterResource(id = R.drawable.ic_round_expand_less_24),
                    contentDescription = ""
                )
            }
        }
        if (isExpanded) {
            PantryTypeCollapsed(pantryItemsMap)
        } else {
            PantryTypeExpanded(
                pantryItemsMap = pantryItemsMap,
                onItemClicked = onItemClicked,
                onEditIconClicked = onEditIconClicked,
                onDeleteIconClicked = onDeleteIconClicked,
                onInputProductNameValueChange = onInputProductNameValueChange,
                onSliderValueChange = onSliderValueChange,
                onUpdateIconClicked = onUpdateIconClicked
            )
        }
        Divider()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PantryTypeCollapsed(pantryItemsMap: Map<PantryItemUiState, IngredientEntity>) {
    Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.horizontalScroll(rememberScrollState())) {

        //move to model later
        val tmp: MutableMap<IngredientEntity, Int> = mutableMapOf()
        pantryItemsMap.forEach { (pantryItemUiState, ingredient) ->
            tmp[ingredient] = (tmp[ingredient] ?: 0) + 1
        }
        tmp.forEach { (ingredient, amount) ->
            ElevatedChipPantryItem(ingredientEntity = ingredient, amount = amount)
        }
    }
}


@Composable
fun PantryTypeExpanded(
    pantryItemsMap: Map<PantryItemUiState, IngredientEntity>,
    onItemClicked: (PantryItemUiState) -> Unit,
    onEditIconClicked: (PantryItemUiState) -> Unit,
    onDeleteIconClicked: (PantryItemUiState) -> Unit,
    onInputProductNameValueChange: (PantryItemUiState, String) -> Unit,
    onSliderValueChange: (PantryItemUiState, Float) -> Unit,
    onUpdateIconClicked: (PantryItemUiState) -> Unit
) {
    Column {
        pantryItemsMap.forEach { (pantryItemUiState, ingredient) ->
            PantryItem(
                item = pantryItemUiState,
                ingredientEntity = ingredient,
                onItemClicked = onItemClicked,
                onAddIconClicked = { },
                onEditIconClicked = onEditIconClicked,
                onDeleteIconClicked = onDeleteIconClicked,
                onInputProductNameValueChange = onInputProductNameValueChange,
                onSliderValueChange = onSliderValueChange,
                onUpdateIconClicked = onUpdateIconClicked
            )
        }
    }
}

@Composable
fun PantryTypeExpandedListItem() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
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
    ElevatedChipPantryItem(IngredientEntity(1, "aaa", "aa", "aa", "aa"), 1)
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
fun ElevatedChipPantryItem(ingredientEntity: IngredientEntity, amount: Int) {
    BadgedBox(
        badge = {
            Badge (modifier = Modifier.padding(0.dp)) {
                val badgeNumber = amount.toString()
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
        ElevatedAssistChip(onClick = { /*TODO*/ }, { Text(text = ingredientEntity.ingredient_name)})
    }
}
