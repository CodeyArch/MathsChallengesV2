package me.goobydev.mathschallengesv2.feature_maths_games.presentation.main_menu

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.components.BottomNavBar
import me.goobydev.mathschallengesv2.feature_maths_games.presentation.util.Screen
import me.goobydev.mathschallengesv2.ui.theme.orbitron

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainMenuScreen(
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavBar(navController, selected = "Home")
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Maths Challenges!",
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = orbitron,
                fontSize = 36.sp
            )
            Button(onClick = {
                navController.navigate(route = Screen.GameModeSelectScreen.route) }
            ) {
                Text("Play")
            }
        }
        
    }
}