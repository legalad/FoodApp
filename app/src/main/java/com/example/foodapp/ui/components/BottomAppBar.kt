package com.example.foodapp.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodapp.R

/*All should be in state, but now its only for test reasons*/
enum class NavigationItem(
    @DrawableRes val painterId: Int,
    @StringRes val descriptionId: Int
    ) {
    PANTRY(R.drawable.ic_round_kitchen_24, R.string.bottom_bar_pantry_description),
    RECIPES(R.drawable.ic_round_menu_book_24, R.string.bottom_bar_recipes_description),
    CALENDAR(R.drawable.ic_round_calendar_month_24, R.string.bottom_bar_calendar_description),
    SHOP_LIST(R.drawable.ic_round_receipt_long_24, R.string.bottom_bar_shop_list_description)
}

@Composable
fun FoodAppNavigationBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val items2: List<NavigationItem> = listOf(NavigationItem.PANTRY, NavigationItem.RECIPES, NavigationItem.CALENDAR, NavigationItem.SHOP_LIST)
    NavigationBar() {
        items2.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {Icon(
                    painter = painterResource(id = item.painterId),
                    contentDescription = stringResource(id = item.descriptionId)
                )},
                label = {Text(item.name)},
                selected = selectedItem == index,
                onClick = { selectedItem = index })

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodAppBottomBarPreview() {
    FoodAppNavigationBar()
}