package com.example.foodapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodapp.R

/*add list witch item to display when user input in text field data (each recomposition list will change)*/
@Composable
fun SearchMenu(isExpanded: Boolean = false) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { /* --> isExpanded = false do when state will be ready*/ }) {
        SearchMenuItem()
        SearchMenuItem()
    }
}

/*it should accept single item as a parameter, item probably ll be a superclass of recipe, ingredient etc*/
@Composable
fun SearchMenuItem() {
    DropdownMenuItem(
        text = { Text("Edit") },
        onClick = { /*TODO*/ }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    onStartValue: String, 
    onValueChange: (String) -> Unit,
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = {Icon(imageVector = Icons.Rounded.Search, contentDescription = "Default description")},
    trailingIcon: (@Composable () -> Unit)? = { Icon(painter = painterResource(id = R.drawable.ic_round_cancel_24), contentDescription = "cancel") }
)

{
    Column(modifier = Modifier) {
        OutlinedTextField(
            value = onStartValue,
            onValueChange = onValueChange,
            label = label,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            placeholder = { Text("ingredientname") },
            singleLine = true
        )
        SearchMenu()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchMenuPreview() {
    Column() {
        SearchTextField(
            onStartValue = "default", 
            onValueChange = {},
            label = { Text(text = "Dodaj...")}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DropdownSearchPreview() {
    SearchMenu()
}

