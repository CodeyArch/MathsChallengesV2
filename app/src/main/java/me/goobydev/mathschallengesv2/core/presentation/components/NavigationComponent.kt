package me.goobydev.mathschallengesv2.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.awards.AwardsScreen
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_over.GameOverScreen
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_play.GamePlayScreen
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
        composable(route = Screen.GamePlayScreen.route,
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.IntType
                    defaultValue = 1
                }
            )
        ) {
            GamePlayScreen(navController = navController)
        }
        composable(
            route = Screen.GameOverScreen.route,
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.IntType
                    defaultValue = 1
                },
                navArgument(
                    name = "lastScore"
                ) {
                    type = NavType.IntType
                    defaultValue = 1
                },
                navArgument(
                    name = "lastTime"
                ) {
                    type = NavType.IntType
                    defaultValue = 1
                },
            )
        ) {
            GameOverScreen(
                navController = navController,
            )
        }
    }
}