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
        getGameModes(state.value.gameModeFilter)
    }
    private fun getGameModes(filter: String) {
        getGameModesJob?.cancel()
        getGameModesJob = when(filter) {
            "Addition" -> {
                gameModesUseCases.getOnlyAddition()
                    .onEach { gameModes ->
                        _state.value = state.value.copy(
                            gameModes = gameModes
                        )
                    }
                    .launchIn(viewModelScope)
            }
            "Subtraction" -> {
                gameModesUseCases.getOnlySubtraction()
                    .onEach { gameModes ->
                        _state.value = state.value.copy(
                            gameModes = gameModes
                        )
                    }
                    .launchIn(viewModelScope)
            }
            "Multiplication" -> {
                gameModesUseCases.getOnlyMultiplication()
                    .onEach { gameModes ->
                        _state.value = state.value.copy(
                            gameModes = gameModes
                        )
                    }
                    .launchIn(viewModelScope)
            }
            "Division" -> {
                gameModesUseCases.getOnlyDivision()
                    .onEach { gameModes ->
                        _state.value = state.value.copy(
                            gameModes = gameModes
                        )
                    }
                    .launchIn(viewModelScope)
            }
            "Mixed" -> {
                gameModesUseCases.getOnlyMixed()
                    .onEach { gameModes ->
                        _state.value = state.value.copy(
                            gameModes = gameModes
                        )
                    }
                    .launchIn(viewModelScope)
            }
            "All" -> {
                when(state.value.gameModeSort) {
                    "Difficulty" -> {
                        gameModesUseCases.getGameModesSortedByDifficulty()
                            .onEach { gameModes ->
                                _state.value = state.value.copy(
                                    gameModes = gameModes
                                )
                            }
                            .launchIn(viewModelScope)
                    }
                    "Subtype" -> {
                        gameModesUseCases.getGameModesSortedBySubtype()
                            .onEach { gameModes ->
                                _state.value = state.value.copy(
                                    gameModes = gameModes
                                )
                            }
                            .launchIn(viewModelScope)
                    }
                    else -> {
                        gameModesUseCases.getGameModes()
                            .onEach { gameModes ->
                                _state.value = state.value.copy(
                                    gameModes = gameModes
                                )
                            }
                            .launchIn(viewModelScope)
                    }
                }

            }
            else -> {
                gameModesUseCases.getGameModes()
                    .onEach { gameModes ->
                        _state.value = state.value.copy(
                            gameModes = gameModes
                        )
                    }
                    .launchIn(viewModelScope)
            }
        }

    }
    fun onEvent(event: GameModeSelectEvent) {
        when(event) {
            GameModeSelectEvent.ToggleFilterSection -> {
                _state.value = state.value.copy(
                    isFilterSectionVisible = !state.value.isFilterSectionVisible
                )
            }
            is GameModeSelectEvent.Filter -> {
                _state.value = state.value.copy(
                    gameModeFilter = event.value
                )
                getGameModes(state.value.gameModeFilter)
            }
            is GameModeSelectEvent.Sort -> {
                _state.value = state.value.copy(
                    gameModeSort = event.value
                )
                getGameModes(state.value.gameModeFilter)
            }
        }
    }
}