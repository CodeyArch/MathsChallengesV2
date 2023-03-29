package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.goobydev.mathschallengesv2.core.presentation.components.BackTopAppBar
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select.components.FilterSection
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select.components.GameModeItem
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select.components.SortSection
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.util.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameModeSelectScreen(
    navController: NavController,
    viewModel: GameModeSelectViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    Scaffold(topBar = {
        BackTopAppBar {
            navController.navigateUp()
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {
                    viewModel.onEvent(GameModeSelectEvent.ToggleFilterSection)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Sort,
                    contentDescription = "Filters",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    },
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(Modifier.fillMaxSize()) {
            AnimatedVisibility(
                visible = state.isFilterSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)) {
                    FilterSection(
                        modifier = Modifier.testTag("FILTER_SECTION"),
                        filter = state.gameModeFilter,
                        onFilterChange = {
                            viewModel.onEvent(GameModeSelectEvent.Filter(it))
                        }
                    )
                    SortSection(
                        modifier = Modifier.testTag("FILTER_SECTION"),
                        sort = state.gameModeSort,
                        onSortChange = {
                            viewModel.onEvent(GameModeSelectEvent.Sort(it))
                        }
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
                items(state.gameModes) { gameMode ->

                    GameModeItem(
                        gameMode = gameMode,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.GamePlayScreen.route.replace(
                                        "{id}",
                                        gameMode.id.toString()
                                    )
                                )
                            }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

    }
}