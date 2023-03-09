package com.example.spacenews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
                    MainNav()
                }
            }
        }
    }
}
