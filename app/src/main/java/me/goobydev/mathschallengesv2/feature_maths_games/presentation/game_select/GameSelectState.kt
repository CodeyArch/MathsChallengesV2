package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select

import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode

data class GameSelectState(
    val gameModes: List<MathsGameMode> = emptyList(),
    val gameModeFilter: String = "All",
    val gameModeSort: String = "Difficulty",
    val isFilterSectionVisible: Boolean = false
)
