package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_play

import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode

data class GamePlayState(
    val gameMode: MathsGameMode = MathsGameMode("", "", "", 0, 0, 0, 0)
)
