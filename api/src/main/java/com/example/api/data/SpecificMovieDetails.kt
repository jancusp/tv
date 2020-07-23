package com.example.api.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SpecificMovieDetails (
    val id: Int = -1,
    @Json(name = "original_title" ) val title: String = "",
    val runtime: Int = -1,
    @Json(name = "poster_path") val posterPath: String = "",
    @Json(name = "backdrop_path") val backDropPath: String = ""
)