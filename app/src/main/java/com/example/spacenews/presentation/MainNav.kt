package com.example.spacenews.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun MainNav(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ArticleScreen.route
    ){
        composable(
            route = Screen.ArticleScreen.route
        ) {
            SpaceNewsScreen(navController)
        }
        composable(
            route = Screen.ArticleDetailScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 1
                }
            )
        ) {
            val id = it.arguments?.getInt("id")
            DetailScreen(navController, id!!)
        }
    }

}


