package org.example.project

import kotlinx.serialization.Serializable
@Serializable
data class Student(
    val id: Long? = null,
    val stuName: String,
    val stuCity: String,
    val stuMarks: Double
)
