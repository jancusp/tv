package com.example.api.data

class MovieHolder(val movie: MovieDetails = MovieDetails())

class MovieSearchResult(val movies: MutableList<MovieHolder> = mutableListOf())
