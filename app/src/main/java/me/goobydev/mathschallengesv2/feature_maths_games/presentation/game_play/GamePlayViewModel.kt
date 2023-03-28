package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_play

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode
import me.goobydev.mathschallengesv2.feature_maths_games.domain.use_cases.GameModesUseCases
import javax.inject.Inject

@HiltViewModel
class GamePlayViewModel @Inject constructor (
    private val gameModesUseCases: GameModesUseCases,
    savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
    private val _state = mutableStateOf(GamePlayState())
    val state: MutableState<GamePlayState> = _state

    private val _score = mutableStateOf(0)
    val score: MutableState<Int> = _score

    private val _questionsAnswered = mutableStateOf(0)
    val questionsAnswered: MutableState<Int> = _questionsAnswered

    private val _time = mutableStateOf(0)
    val time: MutableState<Int> = _time

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            viewModelScope.launch {
                _state.value = state.value.copy(
                    gameMode = gameModesUseCases.getGameMode(id) ?: MathsGameMode("", "", "", 0, 0, 0, 0)
                )
            }
        }
    }
    fun onEvent(event: GamePlayEvent) {
        when(event) {
            is GamePlayEvent.AddToScore -> {
                _score.value = score.value + event.value
                val gameMode = MathsGameMode(
                    state.value.gameMode.gameMode,
                    state.value.gameMode.gameModeSubType,
                    state.value.gameMode.gameModeDifficulty,
                    score.value,
                    state.value.gameMode.maximumScore,
                    state.value.gameMode.time,
                    state.value.gameMode.maximumTime
                )
                _state.value = state.value.copy(gameMode = gameMode)
                println("Total score: ${_score.value}")
            }
            is GamePlayEvent.AddToTime -> {
                viewModelScope.launch {
                    _time.value = time.value + event.value
                    val gameMode = MathsGameMode(
                        state.value.gameMode.gameMode,
                        state.value.gameMode.gameModeSubType,
                        state.value.gameMode.gameModeDifficulty,
                        state.value.gameMode.score,
                        state.value.gameMode.maximumScore,
                        time.value,
                        state.value.gameMode.maximumTime
                    )
                    _state.value = state.value.copy(gameMode = gameMode)
                    if (_time.value == state.value.gameMode.maximumTime) {
                        _eventFlow.emit(UiEvent.TimerEnd)
                    }
                    println("Total time: ${_time.value}")
                }

            }
            is GamePlayEvent.QuestionAnswered -> {
                // Here we add an if statement i.e if answer is correct then add to score
                viewModelScope.launch {
                    onEvent(GamePlayEvent.AddToScore(1))
                    _questionsAnswered.value = questionsAnswered.value + event.value
                    if (_questionsAnswered.value == state.value.gameMode.maximumScore) {
                        _eventFlow.emit(UiEvent.AllQuestionsAnswered)
                    }
                    println("Questions answered: ${_questionsAnswered.value}")
                }

            }
        }
    }
    sealed class UiEvent {
        object TimerEnd: UiEvent()
        object AllQuestionsAnswered: UiEvent()
    }


}