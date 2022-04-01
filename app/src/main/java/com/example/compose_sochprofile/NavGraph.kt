package com.example.compose_sochprofile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun navGraph1(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screens.s1.path){
      composable(Screens.s1.path){s1()}
      composable(Screens.s2.path){s2()}
    }
}