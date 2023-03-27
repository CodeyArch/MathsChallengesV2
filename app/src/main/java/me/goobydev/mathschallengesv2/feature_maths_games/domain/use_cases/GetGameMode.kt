package me.goobydev.mathschallengesv2.feature_maths_games.domain.use_cases

import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode
import me.goobydev.mathschallengesv2.feature_maths_games.domain.repository.MathsGameModesRepository

class GetGameMode(
    private val repository: MathsGameModesRepository
) {
    suspend operator fun invoke(id: Int): MathsGameMode? {
        return repository.getGameModeById(id)
    }
}