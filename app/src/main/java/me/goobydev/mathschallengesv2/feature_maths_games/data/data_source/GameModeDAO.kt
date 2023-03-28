package me.goobydev.mathschallengesv2.feature_maths_games.data.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode

@Dao
interface GameModeDAO {

    @Query("SELECT * FROM mathsGameModes ")
    fun getGameModes(): Flow<List<MathsGameMode>>

    @Query("SELECT * FROM mathsGameModes WHERE gamemodesubtype=\"Addition\"")
    fun getOnlyAddition(): Flow<List<MathsGameMode>>

    @Query("SELECT * FROM mathsGameModes WHERE gamemodesubtype=\"Subtraction\"")
    fun getOnlySubtraction(): Flow<List<MathsGameMode>>

    @Query("SELECT * FROM mathsGameModes WHERE gamemodesubtype=\"Multiplication\"")
    fun getOnlyMultiplication(): Flow<List<MathsGameMode>>

    @Query("SELECT * FROM mathsGameModes WHERE gamemodesubtype=\"Division\"")
    fun getOnlyDivision(): Flow<List<MathsGameMode>>

    @Query("SELECT * FROM mathsGameModes WHERE gamemodesubtype=\"Mixed\"")
    fun getOnlyMixed(): Flow<List<MathsGameMode>>

    @Query("SELECT * FROM mathsGameModes WHERE id = :id")
    suspend fun getGameModesById(id: Int): MathsGameMode?

    @Update
    suspend fun updateGameMode(mathsGameMode: MathsGameMode)

}