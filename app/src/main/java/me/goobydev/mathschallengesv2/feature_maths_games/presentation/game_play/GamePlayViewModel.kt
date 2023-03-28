package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_play

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
class GamePlayViewModel @Inject constructor (
    private val gameModesUseCases: GameModesUseCases,
    savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
    private val _state = mutableStateOf(GamePlayState())
    val state: MutableState<GamePlayState> = _state

    init {
        savedStateHandle.get<Int>("id")?.let { id ->
            viewModelScope.launch {
                _state.value = state.value.copy(
                    gameMode = gameModesUseCases.getGameMode(id) ?: MathsGameMode("", "", "", 0, 0, 0, 0)
                )

            }
        }
    }




    }