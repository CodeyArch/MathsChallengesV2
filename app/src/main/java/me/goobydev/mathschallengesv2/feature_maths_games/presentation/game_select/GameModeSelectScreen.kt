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
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode
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
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val jsonAdapter = moshi.adapter(MathsGameMode::class.java).lenient()
                val mathsGameModeJson = jsonAdapter.toJson(gameMode)
                GameModeItem(
                    gameMode = gameMode,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screen.GamePlayScreen.route.replace("{gameMode}", mathsGameModeJson)
                            )
                        }
                )
                println("Received")

            }
        }
    }
}