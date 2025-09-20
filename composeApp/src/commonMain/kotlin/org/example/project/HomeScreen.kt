
package org.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator


class HomeScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.current

        MaterialTheme {
            val screenModel = rememberScreenModel { RegisterScreenModel() }

            // Observe state flows
            val students by screenModel.students.collectAsState()
            val result by screenModel.result.collectAsState()

            // Snackbar host
            val snackbarHostState = remember { SnackbarHostState() }

            // Automatically fetch when screen loads
            LaunchedEffect(Unit) {
                screenModel.loadStudents()
            }

            // Show snackbar whenever result changes
            LaunchedEffect(result) {
                if (result.isNotEmpty()) {
                    snackbarHostState.showSnackbar(result)
                }
            }

            // ✅ Use Scaffold for bottom snackbar
            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues) // ensures content is not hidden behind snackbar
                        .padding(16.dp)
                ) {
                    Text("Welcome to the Home Screen!", fontSize = 22.sp)

                    Spacer(modifier = Modifier.height(12.dp))

                    Spacer(modifier = Modifier.height(16.dp))

                    // You can remove the old result Text because snackbar shows it
                    // LazyColumn to display students
//                    LazyColumn(
//                        modifier = Modifier.fillMaxSize(),
//                        verticalArrangement = Arrangement.spacedBy(12.dp)
//                    ) {
//                        items(students) { student ->
//                            Card(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .wrapContentHeight(),
//                                colors = CardDefaults.cardColors(containerColor = Color(0xFF1565C0))
//                            ) {
//                                Column(modifier = Modifier.padding(16.dp)) {
//                                    Text(
//                                        text = "ID: ${student.stuId ?: "N/A"}",
//                                        color = Color.White,
//                                        fontSize = 16.sp
//                                    )
//                                    Text(
//                                        text = "Name: ${student.stuName}",
//                                        color = Color.White,
//                                        fontSize = 18.sp
//                                    )
//                                    Text(
//                                        text = "City: ${student.stuCity}",
//                                        color = Color.White,
//                                        fontSize = 16.sp
//                                    )
//                                    Text(
//                                        text = "Marks: ${student.stuMarks}",
//                                        color = Color.White,
//                                        fontSize = 16.sp
//                                    )
//                                }
//                            }
//                        }
//                    }



                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(students) { student ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF1565C0))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Student info
                                    Column {
                                        Text("ID: ${student.id ?: "N/A"}", color = Color.White, fontSize = 16.sp)
                                        Text("Name: ${student.stuName}", color = Color.White, fontSize = 18.sp)
                                        Text("City: ${student.stuCity}", color = Color.White, fontSize = 16.sp)
                                        Text("Marks: ${student.stuMarks}", color = Color.White, fontSize = 16.sp)
                                    }

                                    // Edit & Delete icons
                                    Row {
                                        IconButton(onClick = { navigator?.push(RegisterScreen(student))}) {
                                            Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.Yellow)
                                        }
                                        IconButton(onClick = { screenModel.deleteStudent(student.id) }) {
                                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
