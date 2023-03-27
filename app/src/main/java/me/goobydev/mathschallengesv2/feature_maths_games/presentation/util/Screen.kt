package me.goobydev.mathschallengesv2.feature_maths_games.presentation.util

sealed class Screen(val route: String) {
    object MainMenuScreen: Screen("main_menu_screen")
    object MultipleChoiceMathsGameScreen: Screen("multiple_choice_maths_game_screen")
    object AwardsScreen: Screen("awards_screen")
    object SettingsScreen: Screen("settings_screen")
    object AboutScreen: Screen("about_screen")
    object GameModeSelectScreen: Screen("game_mode_select_screen")
}
