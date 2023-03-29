package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import me.goobydev.mathschallengesv2.feature_maths_games.domain.model.MathsGameMode
import me.goobydev.mathschallengesv2.ui.theme.*

@Composable
fun GameModeItem(
    gameMode: MathsGameMode,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .padding(horizontal = 12.dp)
        .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        )
        .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
        .background(
            when (gameMode.gameModeDifficulty) {
                "Easy" -> Green400
                "Medium" -> Yellow400
                "Hard" -> Red400
                "Impossible" -> Purple400
                "Score Rush" -> Orange400
                else -> Blue400
            }
        )
        .clip(RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(16.dp)

                ) {
                    Text(
                        text = gameMode.gameModeSubType,
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = gameMode.gameModeDifficulty,
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = "Best Score: ${gameMode.score}",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = "Best Time: ${gameMode.time}",
                        style = MaterialTheme.typography.body1
                    )
                }
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth()
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                        .background(
                            Color.White
                        )
                        .clip(RoundedCornerShape(8.dp)),
                    ) {
                        Icon(
                            modifier = Modifier.size(60.dp),
                            imageVector = when(gameMode.gameModeSubType) {
                                "Addition" -> Icons.Filled.Add
                                "Subtraction" -> ImageVector.vectorResource(id = me.goobydev.mathschallengesv2.R.drawable.minus)
                                "Multiplication" -> Icons.Filled.Close
                                "Division" -> ImageVector.vectorResource(id = me.goobydev.mathschallengesv2.R.drawable.divide)
                                "Mixed" -> Icons.Filled.QuestionMark
                                else -> {
                                    Icons.Filled.Add
                                }
                            },
                            contentDescription = "Icon",
                            tint = MaterialTheme.colors.onSurface
                        )
                    }

                }


            }

        }
    }

}