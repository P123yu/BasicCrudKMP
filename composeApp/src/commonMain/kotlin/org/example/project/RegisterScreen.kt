package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
//import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class RegisterScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        MaterialTheme {
//            var name by remember { mutableStateOf("") }  // state to store input
//            var email by remember { mutableStateOf("") }  // state to store input
//            var password by remember { mutableStateOf("") }  // state to store input
//            var confirmPassword by remember { mutableStateOf("") }  // state to store input



            val screenModel = rememberScreenModel { RegisterScreenModel() }

            val stuName by screenModel.stuName.collectAsState()
            val stuCity by screenModel.stuCity.collectAsState()
            val stuMarks by screenModel.stuMarks.collectAsState()
//            val students by screenModel.students.collectAsState()





            Column(
                modifier = Modifier.fillMaxSize()
                    .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.fillMaxHeight(0.2f))

                Text("Welcome Onboard !", fontSize = 40.sp, color = Color.Blue)

                Spacer(modifier = Modifier.fillMaxHeight(0.05f))

//                OutlinedTextField(
//                    value = name,
//                    onValueChange = { name = it },
//                    label = { Text("Full Name") },
//                    modifier = Modifier.fillMaxWidth(0.7f) // takes 70% of parent width
//                )
//
//                Spacer(modifier = Modifier.fillMaxHeight(0.05f))
//
//                OutlinedTextField(
//                    value = email,
//                    onValueChange = { email = it },
//                    label = { Text("Email") },
//                    modifier = Modifier.fillMaxWidth(0.7f) // takes 70% of parent width
//                )
//
//
//                Spacer(modifier = Modifier.fillMaxHeight(0.05f))
//
//                OutlinedTextField(
//                    value = password,
//                    onValueChange = { password = it },
//                    label = { Text("Password") },
//                    modifier = Modifier.fillMaxWidth(0.7f) // takes 70% of parent width
//                )
//
//
//
//                Spacer(modifier = Modifier.fillMaxHeight(0.05f))
//
//
//                OutlinedTextField(
//                    value = confirmPassword,
//                    onValueChange = { confirmPassword = it },
//                    label = { Text("Confirm Password") },
//                    modifier = Modifier.fillMaxWidth(0.7f),
//                    visualTransformation = PasswordVisualTransformation(),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//                )
//


                OutlinedTextField(
                    value = stuName,
                    onValueChange = { screenModel.stuName.value = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth(0.7f)
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = stuCity,
                    onValueChange = { screenModel.stuCity.value = it },
                    label = { Text("City") },
                    modifier = Modifier.fillMaxWidth(0.7f)
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = stuMarks,
                    onValueChange = { screenModel.stuMarks.value = it },
                    label = { Text("Marks") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(0.7f)
                )


                Spacer(modifier = Modifier.fillMaxHeight(0.10f))


//
//                Button(onClick = { screenModel.addStudent() }) {
//                    Text("Save Student")
//                }
//

                Button(
                    onClick = { screenModel.addStudent() },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.25f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text("Register", color = Color.White, fontSize = 20.sp)
                }



                Spacer(modifier = Modifier.fillMaxHeight(0.10f))


                Text(
                    text = buildAnnotatedString {
                        append("Already have an account? ")
                        withStyle(style = SpanStyle(color = Color.Red)) {
                            append("Login")
                        }
                    },
                    fontSize = 15.sp,
                    color = Color.Blue, // default color for the first part
                    modifier = Modifier.clickable {
                        navigator?.push(LoginScreen())
                    }
                )

            }
        }
    }
}



