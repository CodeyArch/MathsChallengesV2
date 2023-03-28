package me.goobydev.mathschallengesv2.feature_maths_games.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mathsGameModes")
data class MathsGameMode(
    val gameMode: String,
    val gameModeSubType: String,
    val gameModeDifficulty: String,
    val score: Int,
    val maximumScore: Int,
    val time: Int,
    val maximumTime: Int,
    @PrimaryKey(autoGenerate = false) val id: Int? = null // Identity in database, used to access game modes
)
