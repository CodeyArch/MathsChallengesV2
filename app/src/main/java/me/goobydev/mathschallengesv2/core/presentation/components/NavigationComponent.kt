package me.goobydev.mathschallengesv2.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.squareup.moshi.Moshi

import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.awards.AwardsScreen
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
        composable(route = Screen.GamePlayScreen.route) { backStackEntry ->
            val mathsGameModeJson =  backStackEntry.arguments?.getString("gameMode")
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val jsonAdapter = moshi.adapter(MathsGameMode::class.java).lenient()
            val mathsGameModeObject = jsonAdapter.fromJson(mathsGameModeJson.toString())

            if (mathsGameModeObject != null) {
                GamePlayScreen(navController = navController, gameMode = mathsGameModeObject)
            }
        }
    }
}