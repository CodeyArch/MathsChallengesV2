package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_play.components


import android.content.res.Configuration
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LargeButton(text: String, onClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    Button(
        onClick = onClick,
        modifier = if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
        } else {
            Modifier
                .padding(12.dp)
                .fillMaxWidth()
        }
    ) {
        Text(
            text =  text,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center,
        )
    }
}