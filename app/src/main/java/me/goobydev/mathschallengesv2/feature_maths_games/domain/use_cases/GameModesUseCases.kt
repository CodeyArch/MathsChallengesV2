package me.goobydev.mathschallengesv2.feature_maths_games.domain.use_cases

data class GameModesUseCases(
    val getGameModes: GetGameModes,
    val getOnlyAddition: GetOnlyAddition,
    val getOnlySubtraction: GetOnlySubtraction,
    val getOnlyMultiplication: GetOnlyMultiplication,
    val getOnlyDivision: GetOnlyDivision,
    val getOnlyMixed: GetOnlyMixed,
    val getGameMode: GetGameMode,
    val updateGameMode: UpdateGameMode
)
