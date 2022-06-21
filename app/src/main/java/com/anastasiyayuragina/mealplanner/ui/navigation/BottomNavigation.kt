package com.anastasiyayuragina.mealplanner.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.anastasiyayuragina.mealplanner.R
import com.anastasiyayuragina.mealplanner.ui.theme.BastilleGrey

sealed class BottomBarItem(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
) {
    object DishList : BottomBarItem(
        route = "1",
        icon = R.drawable.ic_menu_book,
        title = R.string.dish_plan
    )

    object ProductList : BottomBarItem(
        route = "2",
        icon = R.drawable.ic_shopping_cart,
        title = R.string.grocery_list
    )

    object DishHistory : BottomBarItem(
        route = "3",
        icon = R.drawable.ic_history,
        title = R.string.dish_history
    )
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(BottomBarItem.DishList, BottomBarItem.ProductList, BottomBarItem.DishHistory)

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title)
                    )
                },
                unselectedContentColor = BastilleGrey,
                selectedContentColor = MaterialTheme.colors.onPrimary,
                selected = currentRoute == item.route,
                label = { Text(text = stringResource(id = item.title)) },
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}