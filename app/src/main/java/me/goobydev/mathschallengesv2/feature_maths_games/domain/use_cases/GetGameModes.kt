package me.goobydev.mathschallengesv2.feature_maths_games.domain.use_cases

import kotlinx.coroutines.flow.Flow
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode
import me.goobydev.mathschallengesv2.feature_maths_games.domain.repository.MathsGameModesRepository

class GetGameModes(
    private val repository: MathsGameModesRepository
) {
    operator fun invoke(): Flow<List<MathsGameMode>> {
        return repository.getGameModes()
    }
}