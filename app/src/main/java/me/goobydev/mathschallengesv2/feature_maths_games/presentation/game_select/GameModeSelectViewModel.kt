package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.goobydev.mathschallengesv2.feature_maths_games.domain.use_cases.GameModesUseCases
import javax.inject.Inject

@HiltViewModel
class GameModeSelectViewModel @Inject constructor (
    private val gameModesUseCases: GameModesUseCases
) : ViewModel() {
    private val _state = mutableStateOf(GameSelectState())
    val state: MutableState<GameSelectState> = _state

    private var getGameModesJob: Job? = null

    init {
        getGameModes()
    }
    private fun getGameModes() {
        getGameModesJob?.cancel()
        getGameModesJob = gameModesUseCases.getGameModes()
            .onEach { gameModes ->
                _state.value = state.value.copy(
                    gameModes = gameModes
                )
            }
            .launchIn(viewModelScope)
    }
}