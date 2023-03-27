package me.goobydev.mathschallengesv2.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.awards.AwardsScreen
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select.GameModeSelectScreen
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.main_menu.MainMenuScreen
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.settings.SettingsScreen
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
        composable(route = Screen.AwardsScreen.route) {
            AwardsScreen(navController = navController)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
        composable(route = Screen.GameModeSelectScreen.route) {
            GameModeSelectScreen(navController = navController)
        }
    }
}