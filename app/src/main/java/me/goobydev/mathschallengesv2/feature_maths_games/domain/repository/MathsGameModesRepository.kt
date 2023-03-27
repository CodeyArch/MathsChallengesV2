package me.goobydev.mathschallengesv2.feature_maths_games.domain.repository

import kotlinx.coroutines.flow.Flow
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode

interface MathsGameModesRepository {
    fun getGameModes(): Flow<List<MathsGameMode>>
    suspend fun getGameModeById(id: Int): MathsGameMode?

}