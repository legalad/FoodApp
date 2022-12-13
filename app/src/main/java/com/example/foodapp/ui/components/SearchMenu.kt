package com.example.foodapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = {Icon(imageVector = Icons.Rounded.Search, contentDescription = "Default description")},
    trailingIcon: (@Composable () -> Unit)? = { Icon(painter = painterResource(id = R.drawable.ic_round_cancel_24), contentDescription = "cancel") },
    onCancelClicked: () -> Unit,
    hideKeyboard: Boolean = false,
    focusManager: FocusManager = LocalFocusManager.current,
    onFocusClear: () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions: KeyboardActions = KeyboardActions(onSearch = {
        focusManager.clearFocus()
    }),
    )

{
    var visibility: MutableState<Boolean> = remember { mutableStateOf(true)}



    Column(modifier = modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)) {
        OutlinedTextField(
            value = onStartValue,
            onValueChange = onValueChange.also {
                visibility.value = onStartValue.isNotEmpty() },
            label = label,
            leadingIcon = leadingIcon,
            trailingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_round_cancel_24),
                    contentDescription = "cancel",
                    modifier = Modifier.clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
                        onCancelClicked()
                        if (visibility.value) visibility.value = !visibility.value
                    },
                    tint = if (visibility.value) MaterialTheme.colorScheme.onSurfaceVariant else Color.Transparent)},
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = modifier
        )
        SearchMenu()
    }
    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchMenuPreview() {
    Column() {
        SearchTextField(
            onStartValue = "default", 
            onValueChange = {},
            label = { Text(text = "Dodaj...")},
            onCancelClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DropdownSearchPreview() {
    SearchMenu()
}

