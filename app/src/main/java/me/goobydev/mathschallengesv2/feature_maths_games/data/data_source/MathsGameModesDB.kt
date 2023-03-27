package me.goobydev.mathschallengesv2.feature_maths_games.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode

@Database(
    entities = [MathsGameMode::class],
    version = 1
)
abstract class MathsGameModesDB: RoomDatabase() {
    abstract val dao: GameModeDAO

    companion object {
        const val DATABASE_NAME = "mathsGameModes_database"
    }
}


