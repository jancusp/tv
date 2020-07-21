package com.example.tv.ui.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.data.MovieSearchResult
import com.example.api.service.FilmService
import javax.inject.Inject

class MovieListViewModel @Inject constructor() : ViewModel() {
    var _movies = MutableLiveData<MovieSearchResult>(MovieSearchResult())
    var movies: LiveData<MovieSearchResult> = _movies


    @Inject
    lateinit var filmService: FilmService

    fun fetchFilms(){
        filmService.fetchFilms(_movies)
    }
}