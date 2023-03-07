package com.example.spacenews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.spacenews.presentation.theme.SpaceNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceNewsTheme {
                installSplashScreen()
                // A surface container using the 'background' color from the theme
                setContent{
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ArticleScreen.route
                    ) {
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
            }
        }
    }
}
