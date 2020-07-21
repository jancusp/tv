package com.example.api.service

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MovieDetails (
    val id: Int = -1,
    val title: String = "",
    @Json(name = "poster_path") val posterPath: String = ""
)