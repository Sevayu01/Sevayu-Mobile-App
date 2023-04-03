package com.example.sevayu.models

import java.io.Serializable

data class Doctor(
    val Intime: String,
    val Outtime: String,
    val _id: String,
    val contact: String,
    val days: List<String>,
    val department: String,
    val experience: String,
    val id: String,
    val name: String,
    val speciality: String
) : Serializable