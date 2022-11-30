package com.example.foodapp.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.ui.components.FoodAppNavigationBar
import com.example.foodapp.ui.components.FoodAppTopAppBar
import com.example.foodapp.ui.navigation.FoodAppNavGraph
import com.example.foodapp.ui.navigation.FoodAppNavigation

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FoodAppScreen() {
    // Create Controller and initialize it
    val navController = rememberNavController()

    Scaffold (
        topBar = {FoodAppTopAppBar()},
        bottomBar = { FoodAppNavigationBar(navController)},
        content = { innerPadding ->
            FoodAppNavGraph(modifier = Modifier.padding(innerPadding), navController = navController)
        })
}

@Preview
@Composable
fun FoodAppScreenPrev() {
    FoodAppScreen()
}