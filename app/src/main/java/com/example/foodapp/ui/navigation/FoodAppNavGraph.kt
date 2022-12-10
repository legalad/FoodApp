package com.example.foodapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.ui.screens.AddIngredientScreen
import com.example.foodapp.ui.screens.BrowseRecipesScreen
import com.example.foodapp.ui.screens.Pantry
import com.example.foodapp.ui.screens.ScheduleScreen
import com.example.foodapp.ui.viewModel.AddIngredientViewModel

@Composable
fun FoodAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = FoodAppDestinations.BROWSE_RECIPES_ROUTE.name
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier) {
        composable(FoodAppDestinations.BROWSE_RECIPES_ROUTE.name) {
            BrowseRecipesScreen()
        }
        composable(FoodAppDestinations.ADD_INGREDIENT_ROUTE.name) {
            val addIngredientViewModel = hiltViewModel<AddIngredientViewModel>()
            AddIngredientScreen(addIngredientViewModel)
        }
        composable(FoodAppDestinations.PANTRY_ROUTE.name) {
            Pantry()
        }
        composable(FoodAppDestinations.SCHEDULE_ROUTE.name) {
            ScheduleScreen()
        }
    }
}