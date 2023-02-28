package com.example.spacenews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spacenews.ui.theme.Screen
import com.example.spacenews.ui.theme.SpaceNewsScreen
import com.example.spacenews.ui.theme.SpaceNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
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
//                        composable(
//                            route = Screen.CoinDetailScreen.route + "/{coinId}"
//                        ) {
//                            CoinDetailScreen()
//                        }
                    }
                }
            }
        }
    }
}
