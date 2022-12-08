package com.example.foodapp.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.ui.components.FoodAppNavigationBar
import com.example.foodapp.ui.components.FoodAppTopAppBar
import com.example.foodapp.ui.navigation.FoodAppNavGraph
import com.example.foodapp.ui.viewModel.FoodAppViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FoodAppScreen() {
    // Create Controller and initialize it
    val navController = rememberNavController()

    // Create ui view model
    val viewModel: FoodAppViewModel = viewModel()

    // Create ui state --> consider to move deeper
    val uiState by viewModel.foodAppUiState.collectAsState()


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