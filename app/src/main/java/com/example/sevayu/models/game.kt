package com.example.sevayu.models

import java.io.Serializable

data class game(
    val actors: String,
    val director: String,
    val genres: List<String>,
    val id: Int,
    val plot: String,
    val posterUrl: String,
    val runtime: String,
    val title: String,
    val year: String
) : Serializable