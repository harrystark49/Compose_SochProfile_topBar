package com.example.compose_sochprofile

import android.widget.Toast
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
//
@Composable
fun TopBar(navcontroller:NavHostController){
    var list= listOf(
        Screens.s1,
        Screens.s2
    )
    val navBackStackEntry by navcontroller.currentBackStackEntryAsState()
    var currentDestination:NavDestination? = navBackStackEntry?.destination

    BottomNavigation() {
        list.forEach{
            AddItem(Screen = it, currentDestination =currentDestination , navController =navcontroller )
        }
    }
}

@Composable
fun RowScope.AddItem(
    Screen:Screens,
    currentDestination:NavDestination?,
    navController:NavHostController
){
    BottomNavigationItem(
        label = {
            Text(text = Screen.title)
        },
        icon = {
            Icon(imageVector = Icons.Default.Settings, contentDescription ="nav" )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route==Screen.path
        } ==true,
        onClick = {
            navController.navigate(Screen.path){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop=true
            }
        },
        //for unselected item visivily
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
    )
}

//@Composable
//fun BottomBar(navController:NavHostController){
//
//    var Screens= listOf(
//        Screens.s1,
//        Screens.s2,
//    )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    var currentDestination = navBackStackEntry?.destination
//
//    BottomNavigation() {
//        Screens.forEach {
//            AddItem(Screen = it, currentDestination = currentDestination, navController = navController)
//        }
//    }
//}
//
//@Composable
//fun RowScope.AddItem(
//    Screen:Screens,
//    currentDestination:NavDestination?,
//    navController:NavHostController
//){
//    BottomNavigationItem(
//        label = {
//            Text(text = Screen.title)
//        },
//        icon = {
//            Icon(imageVector = Icons.Default.Settings, contentDescription ="nav" )
//        },
//        selected = currentDestination?.hierarchy?.any {
//            it.route==Screen.path
//        } ==true,
//        onClick = {
//            navController.navigate(Screen.path){
//                popUpTo(navController.graph.findStartDestination().id)
//                launchSingleTop=true
//            }
//        },
//        //for unselected item visivily
//        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
//    )
//}