package me.goobydev.mathschallengesv2.feature_maths_games.data.repository

import kotlinx.coroutines.flow.Flow
import me.goobydev.mathschallengesv2.feature_maths_games.data.data_source.GameModeDAO
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode
import me.goobydev.mathschallengesv2.feature_maths_games.domain.repository.MathsGameModesRepository

class MathsGameModesRepositoryImpl(
    private val dao: GameModeDAO
) : MathsGameModesRepository {
    override suspend fun updateGameMode(mathsGameMode: MathsGameMode) {
        dao.updateGameMode(mathsGameMode)
    }

    override fun getGameModes(): Flow<List<MathsGameMode>> {
        return dao.getGameModes()
    }

    override suspend fun getGameModeById(id: Int): MathsGameMode? {
        return dao.getGameModesById(id)
    }
}