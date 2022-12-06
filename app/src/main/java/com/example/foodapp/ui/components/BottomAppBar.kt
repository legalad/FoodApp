package com.example.foodapp.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.R
import com.example.foodapp.ui.navigation.FoodAppDestinations

/*All should be in state, but now its only for test reasons*/
enum class NavigationItem(
    @DrawableRes val painterId: Int,
    @StringRes val descriptionId: Int,
    val route: FoodAppDestinations
    ) {
    PANTRY(
        R.drawable.ic_round_kitchen_24,
        R.string.bottom_bar_pantry_description,
        FoodAppDestinations.PANTRY_ROUTE),
    RECIPES(R.drawable.ic_round_menu_book_24, R.string.bottom_bar_recipes_description, FoodAppDestinations.BROWSE_RECIPES_ROUTE),
    CALENDAR(R.drawable.ic_round_calendar_month_24, R.string.bottom_bar_calendar_description, FoodAppDestinations.SCHEDULE_ROUTE),
    SHOP_LIST(R.drawable.ic_round_receipt_long_24, R.string.bottom_bar_shop_list_description, FoodAppDestinations.ADD_INGREDIENT_ROUTE)
}

@Composable
fun FoodAppNavigationBar(navHostController: NavHostController) {
    var selectedItem by remember { mutableStateOf(1) }
    NavigationBar() {
        NavigationItem.values().forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {Icon(
                    painter = painterResource(id = item.painterId),
                    contentDescription = stringResource(id = item.descriptionId)
                )},
                label = {Text(item.name)},
                selected = selectedItem == index,
                onClick = {
                    navHostController.navigate(item.route.name) {
                        navHostController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                    selectedItem = index
                })

        }
    }
}


@Preview(showBackground = true)
@Composable
fun FoodAppBottomBarPreview() {
    FoodAppNavigationBar(navHostController = rememberNavController())
}