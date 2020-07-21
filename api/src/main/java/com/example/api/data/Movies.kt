package com.example.api.data

import com.example.api.service.MovieDetails

class MovieHolder(val movie: MovieDetails = MovieDetails())

class MovieSearchResult(val movies: MutableList<MovieHolder> = mutableListOf())
