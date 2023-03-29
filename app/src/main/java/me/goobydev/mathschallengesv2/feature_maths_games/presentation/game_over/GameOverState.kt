package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_over

import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode

data class GameOverState(
    val gameMode: MathsGameMode = MathsGameMode("", "", "", 0, 0, 0, 0),
)
