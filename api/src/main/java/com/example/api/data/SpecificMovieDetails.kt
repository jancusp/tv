package com.example.api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SpecificMovieDetails (
    val id: Int,
    @Json(name = "original_title" ) val title: String,
    val runtime: Int,
    @Json(name = "poster_path") val posterPath: String
)