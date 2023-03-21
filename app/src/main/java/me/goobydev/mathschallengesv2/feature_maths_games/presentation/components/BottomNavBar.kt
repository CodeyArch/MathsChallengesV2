package me.goobydev.mathschallengesv2.feature_maths_games.presentation.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.util.Screen

@Composable
fun BottomNavBar(
    navController: NavController
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxHeight(0.08f)
    ) {
        IconButton(
            onClick = {
                navController.navigate(route = Screen.MainMenuScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.333f)
                .testTag("HOME_BUTTON")
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Go to main menu",
                modifier = Modifier.fillMaxSize(0.6f)
            )
        }
        IconButton(
            onClick = {
                navController.navigate(route = Screen.AwardsScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .testTag("AWARDS_BUTTON")
        ) {
            Icon(
                imageVector = Icons.Filled.Menu, // TODO: Replace with trophy icon later
                contentDescription = "Go to awards",
                modifier = Modifier.fillMaxSize(0.6f)
            )
        }
        IconButton(
            onClick = {
                navController.navigate(route = Screen.SettingsScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("SETTINGS_BUTTON")
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Go to settings",
                modifier = Modifier.fillMaxSize(0.6f)
            )
        }
    }
}