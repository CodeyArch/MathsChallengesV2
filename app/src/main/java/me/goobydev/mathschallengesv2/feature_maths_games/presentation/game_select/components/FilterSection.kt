package me.goobydev.mathschallengesv2.feature_maths_games.presentation.game_select.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.goobydev.mathschallengesv2.core.presentation.components.DefaultRadioButton

/* Composable to create a section that controls which modes are retrieved from the database.  */
@Composable
fun FilterSection(
    modifier: Modifier = Modifier,
    filter: String,
    onFilterChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "All",
                selected = filter == "All",
                onSelect = { onFilterChange("All") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Addition",
                selected = filter == "Addition",
                onSelect = { onFilterChange("Addition") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Subtraction",
                selected = filter == "Subtraction",
                onSelect = { onFilterChange("Subtraction") }
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Multiplication",
                selected = filter == "Multiplication",
                onSelect = { onFilterChange("Multiplication") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Division",
                selected = filter == "Division",
                onSelect = { onFilterChange("Division") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Mixed",
                selected = filter == "Mixed",
                onSelect = { onFilterChange("Mixed") }
            )
        }

    }
}