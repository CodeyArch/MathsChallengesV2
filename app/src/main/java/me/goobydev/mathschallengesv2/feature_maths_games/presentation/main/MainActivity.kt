package me.goobydev.mathschallengesv2.feature_maths_games.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import dagger.hilt.android.AndroidEntryPoint
import me.goobydev.mathschallengesv2.core.presentation.components.NavigationComponent
import me.goobydev.mathschallengesv2.ui.theme.MathsChallengesV2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MathsChallengesV2Theme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    NavigationComponent()
                }
            }
        }
    }
}
