package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select.components.GameModeItem
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.util.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameModeSelectScreen(
    navController: NavController,
    viewModel: GameModeSelectViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.gameModes) { gameMode ->

                GameModeItem(
                    gameMode = gameMode,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screen.GamePlayScreen.route.replace("{id}", gameMode.id.toString())
                            )
                        }
                )
            }
        }
    }
}