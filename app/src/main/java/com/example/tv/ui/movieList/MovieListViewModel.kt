package com.example.tv.ui.movieList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.data.MovieSearchResult
import com.example.api.service.FilmService
import com.example.tv.SharedPreferences
import javax.inject.Inject

class MovieListViewModel @Inject constructor() : ViewModel() {
    var _movies = MutableLiveData<MovieSearchResult>(MovieSearchResult())
    var movies: LiveData<MovieSearchResult> = _movies

    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var filmService: FilmService

    fun fetchFilms(){
        filmService.fetchFilms(_movies)
    }

    fun initSharedPreferences(context: Context) {
        sharedPreferences =
            SharedPreferences(context)
    }
}