package com.example.foodapp.ui.navigation

import androidx.navigation.NavHostController

class FoodAppNavigation(navHostController: NavHostController) {
    val navigateToBrowseRecipes: () -> Unit = {
        navHostController.navigate(FoodAppDestinations.BROWSE_RECIPES_ROUTE.name) {
            //TODO
        }
    }
    val navigateToPantry: () -> Unit = {
        navHostController.navigate(FoodAppDestinations.PANTRY_ROUTE.name)
    }
    val navigateToSchedule: () -> Unit = {
        navHostController.navigate(FoodAppDestinations.SCHEDULE_ROUTE.name)
    }
    val navigateToAddIngredient: () -> Unit = {
        navHostController.navigate(FoodAppDestinations.ADD_INGREDIENT_ROUTE.name)
    }
    val navigateToCheckout: () -> Unit = {
        navHostController.navigate(FoodAppDestinations.CHECKOUT_ROUTE.name)
    }
}