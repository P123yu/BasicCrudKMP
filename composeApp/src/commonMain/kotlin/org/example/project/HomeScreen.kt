package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class HomeScreen: Screen {
    @Composable
    override fun Content() {
        MaterialTheme {
            Column(modifier = Modifier.safeContentPadding().fillMaxSize()){
                Text("Welcome to the Home Screen!")
            }
        }
    }
}