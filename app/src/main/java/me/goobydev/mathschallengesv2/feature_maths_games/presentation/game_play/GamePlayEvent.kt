package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_play

sealed class GamePlayEvent {
    data class QuestionAnswered(val value: Int, val answer: Int): GamePlayEvent()
    data class AddToScore(val value: Int): GamePlayEvent()
    data class AddToTime(val value: Int): GamePlayEvent()
    object GenerateNewQuestion: GamePlayEvent()
    object UpdateGameMode: GamePlayEvent()
}
