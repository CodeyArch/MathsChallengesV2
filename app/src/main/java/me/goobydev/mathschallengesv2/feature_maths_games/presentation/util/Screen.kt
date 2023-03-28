package me.goobydev.mathschallengesv2.feature_maths_games.presentation.util

sealed class Screen(val route: String) {
    object MainMenuScreen: Screen("main_menu_screen")
    object GamePlayScreen: Screen("game_play_screen/gameMode={gameMode}")
    object AwardsScreen: Screen("awards_screen")
    object SettingsScreen: Screen("settings_screen")
    object AboutScreen: Screen("about_screen")
    object GameModeSelectScreen: Screen("game_mode_select_screen")

}
