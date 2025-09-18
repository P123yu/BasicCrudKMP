package org.example.project


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.jetbrains.compose.resources.painterResource
import uipractice1.composeapp.generated.resources.Res
import uipractice1.composeapp.generated.resources.splash_screen

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        MaterialTheme {
            var email by remember { mutableStateOf("") }  // state to store input
            var password by remember { mutableStateOf("") }  // state to store input

            Column(
                modifier = Modifier.fillMaxSize()
                    .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.fillMaxHeight(0.1f))

                Text("Welcome Back !", fontSize = 40.sp, color = Color.Blue)

                Spacer(modifier = Modifier.fillMaxHeight(0.05f))

                Image(
                    painter = painterResource(Res.drawable.splash_screen),
                    contentDescription = "Splash Screen",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)  // 50% of parent width
                        .clip(RoundedCornerShape(30.dp))
                )

                Spacer(modifier = Modifier.fillMaxHeight(0.05f))


                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(0.7f) // takes 70% of parent width
                )


                Spacer(modifier = Modifier.fillMaxHeight(0.05f))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(0.7f), // takes 70% of parent width
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.fillMaxHeight(0.10f))


                Button(
                    onClick = { navigator?.push(HomeScreen())},
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.25f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text("Login", color = Color.White, fontSize = 20.sp)
                }


                Spacer(modifier = Modifier.fillMaxHeight(0.10f))


                Text(
                    text = buildAnnotatedString {
                        append("Don't have an account? ")
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("Signup")
                        }
                    },
                    fontSize = 15.sp,
                    color = Color.Blue, // default color for the first part
                    modifier = Modifier.clickable {
                        navigator?.push(RegisterScreen())
                    }
                )
            }
        }
    }
}