package me.goobydev.mathschallengesv2.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.main_menu.MainMenuScreen
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.util.Screen

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainMenuScreen.route) {
        // Insert the screen routes here
        composable(route = Screen.MainMenuScreen.route) {
            MainMenuScreen(navController = navController)
        }
    }
}