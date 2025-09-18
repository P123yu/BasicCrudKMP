package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.jetbrains.compose.resources.painterResource
import uipractice1.composeapp.generated.resources.Res
import uipractice1.composeapp.generated.resources.splash_screen


class SplashScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        MaterialTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    "Start Smart",
                    color = Color.Blue,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.W800,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.fillMaxHeight(0.04f))

                Image(
                    painter = painterResource(Res.drawable.splash_screen),
                    contentDescription = "Splash Screen",
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .clip(RoundedCornerShape(30.dp))
                )

                Spacer(modifier = Modifier.fillMaxHeight(0.2f))

                Button(
                    onClick = {
                        // Example: Navigate to another screen (say RegisterScreen)
                         navigator?.push(LoginScreen())
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.17f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text("Get Started", color = Color.White, fontSize = 20.sp)
                }
            }
        }
    }
}