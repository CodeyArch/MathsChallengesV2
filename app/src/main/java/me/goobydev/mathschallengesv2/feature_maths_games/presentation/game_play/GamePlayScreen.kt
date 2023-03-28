package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_play

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import me.goobydev.mathschallengesv2.ui.theme.orbitron

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GamePlayScreen(
    navController: NavController,
    viewModel: GamePlayViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                GamePlayViewModel.UiEvent.TimerEnd -> {
                    navController.navigateUp()
                }
                GamePlayViewModel.UiEvent.AllQuestionsAnswered -> {
                    navController.navigateUp()
                }
            }
        }
    }
    LaunchedEffect(key1 = "Timer") {
        while(state.gameMode.time <= state.gameMode.maximumTime) {
            delay(1_000L)
            viewModel.onEvent(GamePlayEvent.AddToTime(1))
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = state.gameMode.gameModeDifficulty,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = orbitron,
                fontSize = 36.sp
            )
            Button(
                onClick = { viewModel.onEvent(GamePlayEvent.QuestionAnswered(1)) }
            ) {
                Text(
                    text = state.gameMode.score.toString(),
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = orbitron
                )
            }
            Text(
                text = state.gameMode.time.toString(),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = orbitron
            )
        }
    }
}