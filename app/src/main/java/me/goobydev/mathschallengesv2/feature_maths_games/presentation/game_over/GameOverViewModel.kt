package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_over

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode
import me.goobydev.mathschallengesv2.feature_maths_games.domain.use_cases.GameModesUseCases
import javax.inject.Inject

@HiltViewModel
class GameOverViewModel @Inject constructor (
    private val gameModesUseCases: GameModesUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = mutableStateOf(GameOverState())
    val state: MutableState<GameOverState> = _state

    private val _score = mutableStateOf(0)
    val score: MutableState<Int> = _score

    private val _time = mutableStateOf(0)
    val time: MutableState<Int> = _time

//    private val _highScore = mutableStateOf(false)
//    val highScore: MutableState<Boolean> = _highScore

    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            viewModelScope.launch {
                _state.value = state.value.copy(
                    gameMode = gameModesUseCases.getGameMode(id) ?: MathsGameMode("", "", "", 0, 0, 0, 0, 1)
                )
            }
        }
        savedStateHandle.get<Int>("lastScore")?.let { lastScore ->
            viewModelScope.launch {
                _score.value = lastScore
            }
        }
        savedStateHandle.get<Int>("lastTime")?.let { lastTime ->
            viewModelScope.launch {
                _time.value = lastTime
            }
        }
    }
}