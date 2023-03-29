package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_play

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_play.components.LargeButton
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.util.Screen
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
                    navController.navigate(
                        Screen.GameOverScreen.route
                            .replace(
                                oldValue = "{id}", state.gameMode.id.toString()
                            )
                            .replace(
                                oldValue = "{lastScore}", viewModel.score.value.toString()
                            )
                            .replace(
                                oldValue = "{lastTime}", viewModel.time.value.toString()
                            )
                            .replace(
                                oldValue = "{highScore}", viewModel.highScore.value.toString()
                            )
                    )
                }
                GamePlayViewModel.UiEvent.AllQuestionsAnswered -> {
                    navController.navigate(
                        Screen.GameOverScreen.route
                            .replace(
                                oldValue = "{id}", state.gameMode.id.toString()
                            )
                            .replace(
                                oldValue = "{lastScore}", viewModel.score.value.toString()
                            )
                            .replace(
                                oldValue = "{lastTime}", viewModel.time.value.toString()
                            )
                            .replace(
                                oldValue = "{highScore}", viewModel.highScore.value.toString()
                            )
                    )
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
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Score: \n${viewModel.score.value} / ${state.gameMode.maximumScore}",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(16.dp)
                        ,
                    textAlign = TextAlign.Left,
                )
                Text(
                    text = "Time: \n${viewModel.time.value}s / ${state.gameMode.maximumTime}s",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Right,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .background(color = Color.Transparent)
                    .clip(RoundedCornerShape(8.dp))
                    .border(2.dp, Color.Black, RoundedCornerShape(8.dp)),
                contentAlignment = Center
            ) {
                Text(
                    text = viewModel.question.value,
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = orbitron,
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(count = 4) { index ->
                    LargeButton(
                        text = viewModel.answersList.value[index].toString(),
                        onClick =  { viewModel.onEvent(GamePlayEvent.QuestionAnswered(1, viewModel.answersList.value[index])) }
                    )

                }
            }


        }
    }
}