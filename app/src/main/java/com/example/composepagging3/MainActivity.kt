package com.example.composepagging3

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.composepagging3.ui.theme.ComposePagging3Theme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            ComposePagging3Theme {

            }
        }
    }
}