package me.goobydev.mathschallengesv2.feature_maths_games.domain.repository

import kotlinx.coroutines.flow.Flow
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode

interface MathsGameModesRepository {

    fun getGameModes(): Flow<List<MathsGameMode>>
    fun getGameModesSortedByDifficulty(): Flow<List<MathsGameMode>>
    fun getGameModesSortedBySubtype(): Flow<List<MathsGameMode>>
    fun getOnlyAddition(): Flow<List<MathsGameMode>>
    fun getOnlySubtraction(): Flow<List<MathsGameMode>>
    fun getOnlyMultiplication(): Flow<List<MathsGameMode>>
    fun getOnlyDivision(): Flow<List<MathsGameMode>>
    fun getOnlyMixed(): Flow<List<MathsGameMode>>
    suspend fun getGameModeById(id: Int): MathsGameMode?
    suspend fun updateGameMode(mathsGameMode: MathsGameMode)

}