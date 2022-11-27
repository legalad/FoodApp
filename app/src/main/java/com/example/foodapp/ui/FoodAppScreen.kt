package com.example.foodapp.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodapp.ui.components.FoodAppNavigationBar
import com.example.foodapp.ui.components.FoodAppTopAppBar
import com.example.foodapp.ui.screens.AddIngredientScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FoodAppScreen() {
    Scaffold (
        topBar = {FoodAppTopAppBar()},
        bottomBar = { FoodAppNavigationBar()},
        content = { innerPadding ->
            AddIngredientScreen(modifier = Modifier.padding(innerPadding))
        })
}

@Preview
@Composable
fun FoodAppScreenPrev() {
    FoodAppScreen()
}