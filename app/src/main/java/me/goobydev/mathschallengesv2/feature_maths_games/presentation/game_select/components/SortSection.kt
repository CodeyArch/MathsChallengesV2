package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.goobydev.mathschallengesv2.core.presentation.components.DefaultRadioButton

/* Composable to create a section that controls which modes are retrieved from the database.  */
@Composable
fun SortSection(
    modifier: Modifier = Modifier,
    sort: String,
    onSortChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Sort Types",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = 14.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Difficulty",
                selected = sort == "Difficulty",
                onSelect = { onSortChange("Difficulty") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Subtype",
                selected = sort == "Subtype",
                onSelect = { onSortChange("Subtype") }
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

    }
}