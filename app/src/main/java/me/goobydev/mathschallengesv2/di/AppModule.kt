package me.goobydev.mathschallengesv2.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.goobydev.mathschallengesv2.feature_maths_games.data.data_source.MathsGameModesDB
import me.goobydev.mathschallengesv2.feature_maths_games.data.repository.MathsGameModesRepositoryImpl
import me.goobydev.mathschallengesv2.feature_maths_games.domain.repository.MathsGameModesRepository
import me.goobydev.mathschallengesv2.feature_maths_games.domain.use_cases.GameModesUseCases
import me.goobydev.mathschallengesv2.feature_maths_games.domain.use_cases.GetGameMode
import me.goobydev.mathschallengesv2.feature_maths_games.domain.use_cases.GetGameModes
import me.goobydev.mathschallengesv2.feature_maths_games.domain.use_cases.UpdateGameMode
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMathsGameModesDatabase(app: Application): MathsGameModesDB {
        return Room.databaseBuilder(
            app,
            MathsGameModesDB::class.java,
            MathsGameModesDB.DATABASE_NAME
        )
            .createFromAsset("mathsGameModes.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideMathsGameModesRepository(db: MathsGameModesDB): MathsGameModesRepository {
        return MathsGameModesRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideMathsGameModesUseCases(repository: MathsGameModesRepository): GameModesUseCases {
        return GameModesUseCases(
            getGameModes = GetGameModes(repository),
            getGameMode = GetGameMode(repository),
            updateGameMode = UpdateGameMode(repository)
        )
    }


}