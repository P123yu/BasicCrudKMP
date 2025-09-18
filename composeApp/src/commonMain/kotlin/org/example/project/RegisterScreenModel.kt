//package org.example.project
//
//
//import cafe.adriel.voyager.core.model.ScreenModel
//import io.ktor.client.*
//import io.ktor.client.call.*
//import io.ktor.client.plugins.contentnegotiation.*
//import io.ktor.client.request.*
//import io.ktor.http.*
//import io.ktor.serialization.kotlinx.json.*
//import kotlinx.coroutines.coroutineScope
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import kotlinx.serialization.json.Json
//import cafe.adriel.voyager.core.model.coroutineScope
//
//class RegisterScreenModel : ScreenModel {
//
//    private val client = HttpClient {
//        install(ContentNegotiation) {
//            json(Json { ignoreUnknownKeys = true })
//        }
//    }
//
//    private val baseUrl = "http://10.0.2.2:8080/students"
//
//    // UI State
//    var stuName = MutableStateFlow("")
//    var stuCity = MutableStateFlow("")
//    var stuMarks = MutableStateFlow("")
//
//    private val _result = MutableStateFlow("")
//    val result: StateFlow<String> = _result
//
//    private val _students = MutableStateFlow<List<Student>>(emptyList())
//    val students: StateFlow<List<Student>> = _students
//
//    fun addStudent() {
//        coroutineScope.launch {
//            try {
//                val student = Student(stuName.value, stuCity.value, stuMarks.value)
//                val saved: Student = client.post("$baseUrl/create") {
//                    contentType(ContentType.Application.Json)
//                    setBody(student)
//                }.body()
//                _result.value = "✅ Saved: ${saved.stuName} (${saved.stuCity})"
//                loadStudents()
//            } catch (e: Exception) {
//                _result.value = "❌ Error: ${e.message}"
//            }
//        }
//    }
//
//    fun loadStudents() {
//        coroutineScope.launch {
//            try {
//                _students.value = client.get(baseUrl).body()
//            } catch (e: Exception) {
//                _result.value = "❌ Error: ${e.message}"
//            }
//        }
//    }
//}

package org.example.project

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RegisterScreenModel : ScreenModel {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    private val baseUrl = "http://10.0.2.2:5000/student"

    // UI State
    var stuName = MutableStateFlow("")
    var stuCity = MutableStateFlow("")
    var stuMarks = MutableStateFlow("")

    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students



    fun addStudent() {
        screenModelScope.launch {
            try {
                val student = Student(
                    stuId = null,
                    stuName = stuName.value,
                    stuCity = stuCity.value,
                    stuMarks = stuMarks.value.toDoubleOrNull() ?: 0.0
                )

                println("Sending student: ${Json.encodeToString(student)}")

                val response = client.post("$baseUrl/create") {
                    contentType(ContentType.Application.Json)
                    setBody(student)
                }

                println("HTTP status: ${response.status}")
                val saved: Student = response.body()
                _result.value = "✅ Saved: ${saved.stuName} (${saved.stuCity})"
                loadStudents()
            } catch (e: Exception) {
                _result.value = "❌ Error: ${e.message}"
                e.printStackTrace()  // This will show full stack trace in Logcat
            }
        }
    }

//
//    fun addStudent() {
//        screenModelScope.launch {   // ✅ correct scope
//            try {
//                val student = Student(
//                    stuId = null,
//                    stuName = stuName.value,
//                    stuCity = stuCity.value,
//                    stuMarks = stuMarks.value.toDoubleOrNull() ?: 0.0
//                )
//
//
//                // ✅ serialize with explicit type
//                val jsonBody = Json.encodeToString<Student>(student)
//                println("Sending student: $jsonBody")
//
//
//                val saved: Student = client.post("$baseUrl/create") {
//                    contentType(ContentType.Application.Json)
//                    setBody(student)
//                }.body()
//
//                _result.value = "✅ Saved: ${saved.stuName} (${saved.stuCity})"
//                loadStudents()
//            } catch (e: Exception) {
//                _result.value = "❌ Error: ${e.message}"
//            }
//        }
//    }

    fun loadStudents() {
        screenModelScope.launch {   // ✅ correct scope
            try {
                _students.value = client.get("$baseUrl/getAll").body()
            } catch (e: Exception) {
                _result.value = "❌ Error: ${e.message}"
            }
        }
    }
}
