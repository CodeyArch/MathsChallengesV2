package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode
import me.goobydev.mathschallengesv2.ui.theme.Blue200

@Composable
fun GameModeItem(
    gameMode: MathsGameMode,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .clip(RoundedCornerShape(4.dp))
        .background(Blue200) // Change based on difficulty
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = gameMode.gameModeSubType
            )
        }
    }

}