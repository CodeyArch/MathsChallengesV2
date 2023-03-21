package me.goobydev.mathschallengesv2.feature_maths_games.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.util.Screen

@Composable
fun BottomNavBar(
    navController: NavController,
    selected: String = "Home"
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxHeight(0.09f)
    ) {
        IconButton(
            onClick = {
                navController.navigate(route = Screen.MainMenuScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.34f)
                .testTag("HOME_BUTTON")
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Go to main menu",
                    modifier = Modifier.fillMaxSize(0.6f),
                    tint = if (selected == "Home") {
                        Color.White
                    } else {
                        Color.DarkGray
                    }
                )
                Text(text = "Home")
            }

        }
        IconButton(
            onClick = {
                navController.navigate(route = Screen.AwardsScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .testTag("AWARDS_BUTTON")
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu, // TODO: Replace with trophy icon later
                    contentDescription = "Go to awards",
                    modifier = Modifier.fillMaxSize(0.6f),
                    tint = if (selected == "Awards") {
                        Color.White
                    } else {
                        Color.DarkGray
                    }
                )
                Text(text = "Awards")
            }

        }
        IconButton(
            onClick = {
                navController.navigate(route = Screen.SettingsScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("SETTINGS_BUTTON")
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Go to settings",
                    modifier = Modifier.fillMaxSize(0.6f),
                    tint = if (selected == "Settings") {
                        Color.White
                    } else {
                        Color.DarkGray
                    }
                )
                Text(text = "Settings")
            }

        }
    }
}