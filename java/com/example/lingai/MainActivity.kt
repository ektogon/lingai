package com.example.lingai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.lingai.presentation.navigation.MainNavigation
import com.example.lingai.presentation.theme.LingaiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            var darkThemeOverride by remember { mutableStateOf<Boolean?>(null) }
            var darkTheme = darkThemeOverride ?: isSystemInDarkTheme()

            LingaiTheme(darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(
                        darkTheme = darkTheme,
                        onThemeUpdate = { darkThemeOverride = !darkTheme }
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}