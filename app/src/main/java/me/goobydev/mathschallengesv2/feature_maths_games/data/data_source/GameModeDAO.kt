package me.goobydev.mathschallengesv2.feature_maths_games.data.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode

@Dao
interface GameModeDAO {

    @Query("SELECT * FROM mathsGameModes ORDER BY \n" +
            "CASE gamemodedifficulty\n" +
            "    WHEN \"Easy\" THEN 1\n" +
            "    WHEN \"Medium\" THEN 2\n" +
            "    WHEN \"Hard\" THEN 3\n" +
            "    WHEN \"Impossible\" THEN 4\n" +
            "    WHEN \"Score Rush\" THEN 5\n" +
            "    ELSE 6\n" +
            "END;")
    fun getGameModesSortedByDifficulty(): Flow<List<MathsGameMode>>

    @Query("SELECT * FROM mathsGameModes ORDER BY \n" +
            "CASE gamemodesubtype\n" +
            "    WHEN \"Addition\" THEN 1\n" +
            "    WHEN \"Subtraction\" THEN 2\n" +
            "    WHEN \"Multiplication\" THEN 3\n" +
            "    WHEN \"Division\" THEN 4\n" +
            "    WHEN \"Mixed\" THEN 5\n" +
            "    ELSE 6\n" +
            "END;")
    fun getGameModesSortedBySubtype(): Flow<List<MathsGameMode>>

    @Query("SELECT * FROM mathsGameModes")
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