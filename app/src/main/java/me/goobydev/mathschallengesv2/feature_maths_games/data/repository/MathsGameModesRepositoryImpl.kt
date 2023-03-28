package me.goobydev.mathschallengesv2.feature_maths_games.data.repository

import kotlinx.coroutines.flow.Flow
import me.goobydev.mathschallengesv2.feature_maths_games.data.data_source.GameModeDAO
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode
import me.goobydev.mathschallengesv2.feature_maths_games.domain.repository.MathsGameModesRepository

class MathsGameModesRepositoryImpl(
    private val dao: GameModeDAO
) : MathsGameModesRepository {

    override fun getGameModes(): Flow<List<MathsGameMode>> {
        return dao.getGameModes()
    }

    override fun getOnlyAddition(): Flow<List<MathsGameMode>> {
        return dao.getOnlyAddition()
    }

    override fun getOnlySubtraction(): Flow<List<MathsGameMode>> {
        return dao.getOnlySubtraction()
    }

    override fun getOnlyMultiplication(): Flow<List<MathsGameMode>> {
        return dao.getOnlyMultiplication()
    }

    override fun getOnlyDivision(): Flow<List<MathsGameMode>> {
        return dao.getOnlyDivision()
    }

    override fun getOnlyMixed(): Flow<List<MathsGameMode>> {
        return dao.getOnlyMixed()
    }

    override suspend fun getGameModeById(id: Int): MathsGameMode? {
        return dao.getGameModesById(id)
    }

    override suspend fun updateGameMode(mathsGameMode: MathsGameMode) {
        dao.updateGameMode(mathsGameMode)
    }
}