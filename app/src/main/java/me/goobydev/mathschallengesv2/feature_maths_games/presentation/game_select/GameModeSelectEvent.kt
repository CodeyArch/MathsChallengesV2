package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select

sealed class GameModeSelectEvent {
    data class Order(val value: String): GameModeSelectEvent()
    object ToggleFilterSection: GameModeSelectEvent()
}