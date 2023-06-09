package me.goobydev.mathschallengesv2.core.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

/* Back Top App Bar Composable, goes back to previous page */
@Composable
fun BackTopAppBar(onNavIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Select Mode") },
        navigationIcon = {
            IconButton(
                onClick = {
                    onNavIconClick()
                },
                modifier = Modifier.testTag("BACK_NAVIGATION")
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back to previous page"
                )
            }
        }
    )
}